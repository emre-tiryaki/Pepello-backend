package org.pepello.dto.events;

import org.pepello.entities.State;

import java.util.UUID;

public record StateCreatedEvent(
        State state,
        UUID projectId) {
}
