package org.pepello.dto.task;

import java.util.UUID;

public record TaskStateChangeRequest(
        UUID newStateId
) {
}
