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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String username;
    @Password
    private String password;
    @Password
    private String confirmPassword;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String about;

}
