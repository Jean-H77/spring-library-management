package org.library.springlibrarymanagement.exception.exceptions;

public class ApiBadRequestException extends RuntimeException {

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
