package org.pepello.dto.auth;

public record AuthResponse(
        String token,
        long expiresIn
) {
}
