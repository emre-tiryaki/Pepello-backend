package org.pepello.dto.user;

import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;
import java.util.UUID;

public record DtoUser(
        UUID id,
        String firstName,
        String lastName,
        String email,
        DtoMedia profilePic,
        LocalDate birthday
) {
}
