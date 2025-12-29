package org.pepello.common.exception.notfound;

import org.pepello.common.exception.base.PepelloException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TaskNotFoundException extends PepelloException {

    public TaskNotFoundException(UUID id) {
        super(
                "Task bulunamadı. id=" + id,
                "TASK_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
