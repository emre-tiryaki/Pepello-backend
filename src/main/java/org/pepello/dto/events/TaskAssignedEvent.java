package org.pepello.dto.events;

import org.pepello.entities.Task;
import org.pepello.entities.User;

public record TaskAssignedEvent(
        User assignee,
        Task task
) {
}
