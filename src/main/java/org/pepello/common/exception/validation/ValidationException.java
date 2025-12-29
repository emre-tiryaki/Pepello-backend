package org.pepello.common.exception.validation;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class ValidationException extends PepelloException {

    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR", HttpStatus.BAD_REQUEST);
    }
}
