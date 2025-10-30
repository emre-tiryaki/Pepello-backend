package org.pepello.dto.comment;

import org.pepello.dto.task.DtoTask;
import org.pepello.dto.user.DtoUser;

import java.util.UUID;

public record DtoComment(
        UUID commentId,
        DtoTask task,
        DtoUser user,
        String text
) {
}
