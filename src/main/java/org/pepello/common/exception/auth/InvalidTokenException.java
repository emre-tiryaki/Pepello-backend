package org.pepello.common.exception.auth;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends PepelloException {

    public InvalidTokenException() {
        super(
                "Geçersiz veya süresi dolmuş token",
                "INVALID_TOKEN",
                HttpStatus.UNAUTHORIZED
        );
    }
}
