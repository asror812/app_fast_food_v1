package com.example.app_fast_food.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@RestControllerAdvice
public class FastFoodExceptionHandler {

    @ExceptionHandler(OtpException.OtpLimitExitedException.class)
    public ResponseEntity<?> handleOtpLimitException(OtpException.OtpLimitExitedException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(msg);
    }

    @ExceptionHandler(OtpException.OtpEarlyResentException.class)
    public ResponseEntity<?> handleEarlyResentException(OtpException.OtpEarlyResentException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(HttpStatus.TOO_EARLY).body(msg);
    }

    @ExceptionHandler(OtpException.PhoneNumberNotVerified.class)
    public ResponseEntity<?> handlePhoneNumberNotVerifiedException(OtpException.PhoneNumberNotVerified e) {
        String msg = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    @ExceptionHandler(RestException.FileSizeLimitExceedException.class)
    public ResponseEntity<?> handleFileSizeLimitException(RestException.FileSizeLimitExceedException e) {
        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    @ExceptionHandler(RestException.EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(RestException.EntityNotFoundException e) {
        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handle(RestException.FileNotFound e) {
        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        for (ObjectError allError : e.getBindingResult().getAllErrors()) {
            FieldError fieldError = (FieldError) allError;
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();

            errors.put(field, defaultMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidOperationException(RestException.InvalidOperationException e){
        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

/*    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception e) {
        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }*/
}
