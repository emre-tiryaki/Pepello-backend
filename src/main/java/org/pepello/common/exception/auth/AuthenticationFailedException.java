package org.pepello.common.exception.auth;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends PepelloException {

    public AuthenticationFailedException() {
        super(
                "Kimlik doğrulama başarısız",
                "AUTHENTICATION_FAILED",
                HttpStatus.UNAUTHORIZED
        );
    }
}
