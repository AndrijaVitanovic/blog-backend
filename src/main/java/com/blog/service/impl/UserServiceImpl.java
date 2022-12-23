package com.blog.service.impl;

import com.blog.data.RegisterDto;
import com.blog.entity.EmailVerificationRequest;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.EmailAlreadyExistsException;
import com.blog.exception.PasswordMismatchException;
import com.blog.repository.UserRepository;
import com.blog.service.EmailVerificationRequestService;
import com.blog.service.RoleService;
import com.blog.service.UserService;
import com.blog.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationRequestService emailVerificationRequestService;
    private final RoleService roleService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found!"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User with that email not found!"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existingUser = findById(user.getId());
        user.setPassword(existingUser.getPassword());
        user.setRecordStatus(existingUser.getRecordStatus());
        user.setRoles(existingUser.getRoles());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void register(RegisterDto registerDto) {
        userRepository.findByEmail(registerDto.email())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException();
                });

        if (!Objects.equals(registerDto.password(), registerDto.confirmPassword())) {
            throw new PasswordMismatchException();
        }

        User user = registerDto.toEntity();
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setDisplayName(StringUtils.generateString());
        save(user);
        emailVerificationRequestService.createVerification(user);
    }

    @Override
    public void verify(String token) {
        EmailVerificationRequest emailVerificationRequest =
                emailVerificationRequestService.verify(token);
        userRepository.findByEmail(emailVerificationRequest.getEmail())
                .ifPresent(this::finalizeRegistration);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with that email not found!"));
    }

    private void finalizeRegistration(User user) {
        user.setEmailVerified(true);
        user.getRoles().add(roleService.findByName(Role.ROLE_USER));
        update(user);
    }
}
