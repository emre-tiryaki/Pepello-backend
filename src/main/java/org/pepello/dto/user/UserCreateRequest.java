package org.pepello.dto.user;

import org.pepello.common.request.BaseCreateRequest;

import java.time.LocalDate;
import java.util.UUID;

//Kullanıcı yaratma için parametreler
public record UserCreateRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        UUID profilePicId,
        LocalDate birthday
) implements BaseCreateRequest {
}
