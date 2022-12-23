package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "error.email.verification.expired", value= HttpStatus.BAD_REQUEST)
public class EmailVerificationExpiredException extends RuntimeException{
}
