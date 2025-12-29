package org.pepello.common.exception.notfound;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class RelationNotFoundException extends PepelloException {

    public RelationNotFoundException(UUID primaryId, UUID relatedId) {
        super(
                "İlişki bulunamadı. primaryId=" + primaryId + ", relatedId=" + relatedId,
                "RELATION_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
