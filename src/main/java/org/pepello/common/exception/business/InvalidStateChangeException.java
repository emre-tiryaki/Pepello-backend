package org.pepello.common.exception.business;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

public class InvalidStateChangeException extends PepelloException {

    public InvalidStateChangeException() {
        super(
                "Task aynı state'e geçirilemez",
                "INVALID_STATE_CHANGE",
                HttpStatus.BAD_REQUEST
        );
    }
}
