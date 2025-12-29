package org.pepello.common.exception.business;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class BusinessException extends PepelloException {

    public BusinessException(String message) {
        super("BUSINESS ERROR",message, HttpStatus.BAD_REQUEST);
    }

}
