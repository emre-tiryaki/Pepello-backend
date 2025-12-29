package org.pepello.common.exception.base;

import org.springframework.http.HttpStatus;

public abstract class PepelloException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    protected PepelloException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
