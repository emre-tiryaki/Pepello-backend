package org.pepello.common.exception.notfound;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UserNotFoundException extends PepelloException {

    public UserNotFoundException(UUID id) {
        super(
                "Kullanıcı bulunamadı. id=" + id,
                "USER_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
