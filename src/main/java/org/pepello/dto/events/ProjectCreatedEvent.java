package org.pepello.dto.events;

import org.pepello.entities.Project;

public record ProjectCreatedEvent(
        Project createdProject
) {
}
