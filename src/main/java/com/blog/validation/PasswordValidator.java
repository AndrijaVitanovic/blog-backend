package com.blog.validation;

import com.blog.validation.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Custom validator class for password validation
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    // Write regex for password validation that validates that password contains at least one uppercase letter,
    // one lowercase letter, one digit and one special character and is at least 8 characters long
    private static final String VALID_PASSWORD_REGEX
            = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(VALID_PASSWORD_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return pattern.matcher(value).matches();
    }
}
