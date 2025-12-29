package org.pepello.common.exception.business;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends PepelloException {

    public EmailAlreadyExistsException(String email) {
        super(
                "Bu email zaten kullanılıyor: " + email,
                "EMAIL_ALREADY_EXISTS",
                HttpStatus.CONFLICT
        );
    }
}
