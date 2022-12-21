package com.blog.service;

import com.blog.data.RegisterUserDto;
import com.blog.entity.User;
import com.blog.exception.PasswordMismatchException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

    /**
     * This method is used to register a new user
     * @param registerUserDto - Dto class that contains most of the user data
     * @throws PasswordMismatchException - if the password and the password confirmation do not match
     */
    @Transactional
    void register(RegisterUserDto registerUserDto);


    @Transactional
    void verify(String token);
}
