package org.pepello.dto.role;

import java.util.UUID;

public record DtoRole(
        UUID id,
        String roleName,
        String roleDescription
) {
}
