package org.pepello.dto.auth;

import java.time.LocalDate;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate birthday
) {
}
