package org.pepello.dto.task;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record TaskUpdateRequest(
        UUID projectId,
        UUID stateId,
        UUID media,
        UUID assignee,
        String taskTitle,
        String taskDescription,
        Boolean isFinished
) implements BaseUpdateRequest {
}
