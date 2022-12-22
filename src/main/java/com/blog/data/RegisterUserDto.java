package com.blog.data;

import com.blog.entity.User;
import com.blog.validation.annotation.Email;
import com.blog.validation.annotation.Password;

/**
 * Dto class for registering new user.
 */
public record RegisterUserDto(
        String username,
        @Password String password,
        @Password String confirmPassword,
        @Email String email,
        String firstName,
        String lastName,
        String about
) {

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setAbout(this.about);
        return user;
    }
}
