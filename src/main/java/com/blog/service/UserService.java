package com.blog.service;

import com.blog.data.RegisterDto;
import com.blog.entity.User;
import com.blog.exception.PasswordMismatchException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    /**
     * Finds all users in database.
     *
     * @return List of users.
     */
    List<User> findAll();

    /**
     * Finds user by given id.
     *
     * @param id Id of the user.
     * @return User with a given id.
     */
    User findById(Long id);

    /**
     * Finds user in database by email.
     *
     * @param email Email of the user.
     * @return User with given email.
     */
    User findByEmail(String email);

    /**
     * Saves user in database.
     *
     * @param user User that needs to be saved.
     * @return Saved user.
     */
    User save(User user);

    /**
     * Updates user in database.
     *
     * @param user User that needs to be updated.
     * @return Updated user.
     */
    User update(User user);

    /**
     * Deletes user in database with given id.
     *
     * @param id Id of the user.
     */
    void deleteById(Long id);

    /**
     * This method is used to register a new user
     *
     * @param registerDto - Dto class that contains most of the user data
     * @throws PasswordMismatchException - if the password and the password confirmation do not match
     */
    @Transactional
    void register(RegisterDto registerDto);


    /**
     * This method is used to verify users email address.
     *
     * @param token Generated token for the verification request.
     */
    @Transactional
    void verify(String token);
}
