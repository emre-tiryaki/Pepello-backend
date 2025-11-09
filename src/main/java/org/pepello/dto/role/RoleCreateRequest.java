package org.pepello.dto.role;

import org.pepello.common.request.BaseCreateRequest;

public record RoleCreateRequest(
        String name,
        String description
) implements BaseCreateRequest {
}
