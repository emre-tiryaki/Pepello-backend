package org.pepello.dto.auth;

import java.util.UUID;

public record AuthResponse(
                String token,
                long expiresIn,
                UUID userId) {
}
