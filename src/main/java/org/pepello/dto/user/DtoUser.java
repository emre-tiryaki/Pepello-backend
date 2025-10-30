package org.pepello.dto.user;

import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;
import java.util.UUID;

public record DtoUser(
        UUID userId,
        DtoMedia profilePic,
        String firstName,
        String lastName,
        String email,
        LocalDate birthday
) {
}
