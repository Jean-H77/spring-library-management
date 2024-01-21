package org.library.springlibrarymanagement.exception.exceptions;

public class ApiResourceNotFoundException extends RuntimeException {

    public ApiResourceNotFoundException(String message) {
        super(message);
    }

    public ApiResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
