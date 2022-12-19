package com.blog.validation;

import com.blog.validation.annotation.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Custom validator class for email validation
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    /**
     * Regex for email and domain validation that validates
     * that email contains at least one @ and one .
     */
    private static final String VALID_EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private static final Pattern pattern = Pattern.compile(VALID_EMAIL_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return pattern.matcher(value).matches();
    }
}
