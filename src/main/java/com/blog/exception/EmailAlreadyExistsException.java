package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "error.email.already-exists", value= HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {
}
