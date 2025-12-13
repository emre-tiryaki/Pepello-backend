package org.pepello.dto.task;

import org.pepello.common.request.BaseCreateRequest;
import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record TaskCreateRequest(
        UUID projectId,
        UUID stateId,
        UUID media,
        UUID assignee,
        String taskTitle,
        String taskDescription
) implements BaseCreateRequest {
}
