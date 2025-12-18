package org.pepello.dto.events;

import org.pepello.entities.Team;
import org.pepello.entities.User;

public record TeamCreatedEvent(
        User user,
        Team team
) {
}
