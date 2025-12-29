package org.pepello.common.exception.notfound;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class RoleNotFoundException extends PepelloException {

    public RoleNotFoundException(UUID id) {
        super(
                "Role bulunamadı. id=" + id,
                "ROLE_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
