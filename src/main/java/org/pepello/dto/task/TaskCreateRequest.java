package org.pepello.dto.task;

import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record TaskCreateRequest(
        UUID projectId,
        UUID stateId,
        DtoMedia media,
        String taskTitle,
        String taskDescription
) {
}
