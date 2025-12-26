package org.pepello.dto.events;

import java.util.UUID;

import org.pepello.entities.Project;

public record ProjectCreatedEvent(
                Project createdProject,
                UUID teamId) {
}
