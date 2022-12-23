package com.blog.validation.annotation;

import com.blog.validation.EmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    String message() default "validation.email.invalid";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
