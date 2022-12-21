package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Email verification already expired!", value= HttpStatus.BAD_REQUEST)
public class EmailVerificationExpiredException extends RuntimeException{
}
