package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Email verification already exists!", value= HttpStatus.BAD_REQUEST)
public class EmailVerificationAlreadyExistsException extends RuntimeException {
}
