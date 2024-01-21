package org.library.springlibrarymanagement.exception.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ApiBadRequestException extends RuntimeException {

    private BindingResult bindingResult;

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiBadRequestException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }
}
