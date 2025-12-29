package org.pepello.common.exception.notfound;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends PepelloException {

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(
                "RESOURCE_NOT_FOUND",
                resource + " not found with " + field + " = " + value,
                HttpStatus.NOT_FOUND
        );
    }

    public ResourceNotFoundException(String message) {
        super(
                "RESOURCE_NOT_FOUND",
                message,
                HttpStatus.NOT_FOUND
        );
    }
}
