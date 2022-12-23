package com.blog.error;

import com.blog.error.data.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.*;

/**
 * This class is responsible for handling all exceptions that are thrown
 * by the application. It returns a proper response to the client.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class ErrorController extends ResponseEntityExceptionHandler {

    private final Environment env;
    private final MessageSource messageSource;

    private static final String UNEXPECTED_ERROR = "error.unexpected";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), messageSource.getMessage(
                    Objects.requireNonNull(error.getDefaultMessage()),
                    null,
                    LocaleContextHolder.getLocale()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), messageSource.getMessage(
                    Objects.requireNonNull(error.getDefaultMessage()),
                    null,
                    LocaleContextHolder.getLocale()));
        }

        return handleExceptionInternal(
                ex, errors, headers, status, request);
    }

    @ExceptionHandler({
            ConstraintViolationException.class
    })
    protected ResponseEntity<Object> handleBadRequest(Exception ex, HttpServletRequest request) {
        return handle(ex, request.getServletPath(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleHibernateConstraintViolation(Exception ex, HttpServletRequest request) {
        return handle(ex.getCause().getCause(), request.getServletPath(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handle(ex, request.getContextPath(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            NoSuchElementException.class
    })
    public ResponseEntity<Object> notFound(Exception ex, HttpServletRequest request) {
        return handle(ex, request.getServletPath(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> defaultHandler(Exception ex, HttpServletRequest request) {
        return handle(ex, request.getServletPath(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handle(Throwable ex, String path, HttpStatus initialStatus) {
        String message = ex.getMessage();
        HttpStatus status = initialStatus;
        if (ex.getClass().getAnnotation(ResponseStatus.class) != null) {
            ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
            if (!responseStatus.reason().isBlank()) {
                if (message == null || message.isBlank()) {
                    message = responseStatus.reason();
                } else {
                    message = String.format("%s: %s", responseStatus.reason(), message);
                }
            } else {
                message = UNEXPECTED_ERROR;
            }

            message = messageSource.getMessage(
                    message,
                    null,
                    LocaleContextHolder.getLocale());

            // We set one that is different from the default value
            if (!responseStatus.code().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                status = responseStatus.code();
            } else if (!responseStatus.value().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                status = responseStatus.value();
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        StringWriter buffer = new StringWriter();
        try (PrintWriter writer = new PrintWriter(buffer)) {
            ex.printStackTrace();
            String trace = null;

            if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
                ex.printStackTrace(writer);
                trace = buffer.toString();
            }

            return new ResponseEntity<>(
                    ErrorResponse.builder()
                            .status(status.value())
                            .error(status.getReasonPhrase())
                            .message(message)
                            .path(path)
                            .timestamp(Instant.now().toString())
                            .trace(trace)
                            .build(),
                    new HttpHeaders(), status);
        }
    }
}
