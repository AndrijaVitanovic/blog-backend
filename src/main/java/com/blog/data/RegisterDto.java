package com.blog.data;

import com.blog.entity.User;
import com.blog.validation.annotation.Email;
import com.blog.validation.annotation.Password;
import jakarta.validation.constraints.NotBlank;

/**
 * Dto class for registering new user.
 */
public record RegisterDto(
        @NotBlank(message = "validation.username.required") String username,
        @Password @NotBlank(message = "validation.password.required") String password,
        @NotBlank(message = "validation.confirmPassword.required") String confirmPassword,
        @Email @NotBlank(message = "validation.email.required") String email,
        @NotBlank(message = "validation.firstName.required") String firstName,
        @NotBlank(message = "validation.lastName.required") String lastName,
        @NotBlank(message = "validation.about.required") String about
) {
    /**
     * Utility class that converts RegisterDto to User entity.
     *
     * @return User entity.
     */
    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAbout(about);
        return user;
    }
}
