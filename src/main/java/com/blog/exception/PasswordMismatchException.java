package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "error.password.mismatch", value= HttpStatus.BAD_REQUEST)
public class PasswordMismatchException extends RuntimeException {
}
