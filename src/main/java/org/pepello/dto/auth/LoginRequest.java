package org.pepello.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
