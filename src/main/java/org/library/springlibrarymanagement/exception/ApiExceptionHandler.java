package org.library.springlibrarymanagement.exception;

import org.library.springlibrarymanagement.exception.exceptions.ApiBadRequestException;
import org.library.springlibrarymanagement.exception.exceptions.ApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiBadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(ApiBadRequestException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ApiResourceNotFoundException exception) {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }
}
