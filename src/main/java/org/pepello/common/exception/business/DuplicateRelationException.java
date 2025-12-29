package org.pepello.common.exception.business;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class DuplicateRelationException extends PepelloException {

    public DuplicateRelationException() {
        super(
                "İlişki zaten mevcut",
                "RELATION_ALREADY_EXISTS",
                HttpStatus.CONFLICT
        );
    }
}
