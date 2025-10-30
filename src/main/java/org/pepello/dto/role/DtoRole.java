package org.pepello.dto.role;

import java.util.UUID;

public record DtoRole(
        UUID roleId,
        String roleName,
        String roleDescription
) {
}
