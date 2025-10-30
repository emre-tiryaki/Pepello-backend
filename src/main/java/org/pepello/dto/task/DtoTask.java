package org.pepello.dto.task;

import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.project.DtoProject;
import org.pepello.dto.state.DtoState;

import java.util.UUID;

public record DtoTask(
        UUID task,
        DtoProject project,
        DtoState state,
        DtoMedia media,
        String taskTitle,
        String taskDescription,
        Boolean isFinished
) {
}
