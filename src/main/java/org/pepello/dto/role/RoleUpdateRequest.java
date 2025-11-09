package org.pepello.dto.role;

import org.pepello.common.request.BaseUpdateRequest;

public record RoleUpdateRequest(
        String name,
        String description
) implements BaseUpdateRequest {
}
