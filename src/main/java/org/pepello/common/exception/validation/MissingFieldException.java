package org.pepello.common.exception.validation;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class MissingFieldException extends PepelloException {

    public MissingFieldException(String field) {
        super(
                field + " alanı zorunludur",
                "MISSING_FIELD",
                HttpStatus.BAD_REQUEST
        );
    }
}
