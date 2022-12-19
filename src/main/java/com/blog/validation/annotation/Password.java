package com.blog.validation.annotation;

import com.blog.validation.PasswordValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// TODO: Localization
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
