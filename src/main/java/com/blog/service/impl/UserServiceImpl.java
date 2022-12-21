package com.blog.service.impl;

import com.blog.data.RegisterUserDto;
import com.blog.data.Template;
import com.blog.entity.User;
import com.blog.exception.EmailAlreadyExistsException;
import com.blog.exception.PasswordMismatchException;
import com.blog.repository.UserRepository;
import com.blog.service.MailService;
import com.blog.service.RoleService;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final MailService mailService;

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
    public void register(RegisterUserDto registerUserDto) {
        if (!Objects.equals(registerUserDto.getPassword(), registerUserDto.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }

        userRepository.findByEmail(registerUserDto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException();
                });
        /*
         * TODO: profile picture
         */
        User user = new User(
                registerUserDto.getUsername(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                registerUserDto.getEmail(),
                registerUserDto.getFirstName(),
                registerUserDto.getLastName(),
                registerUserDto.getAbout(),
                generateDisplayName(registerUserDto.getFirstName(), registerUserDto.getLastName())
        );
        save(user);

        // TODO: Create email verification system, so that we can avoid sending email here.
        mailService.sendMail(
                user.getEmail(),
                "Welcome to Spotlight!",
                new Template("verification-mail"),
                Map.of("name", user.getFirstName())
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    private String generateDisplayName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
