package com.blog.data;

import com.blog.validation.annotation.Email;
import com.blog.validation.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
