package org.library.springlibrarymanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.HashMap;

@Getter
public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    private HashMap<String, String> errors;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, HashMap<String, String> errors) {
        this(message, httpStatus, timestamp);
        this.errors = errors;
    }
}
