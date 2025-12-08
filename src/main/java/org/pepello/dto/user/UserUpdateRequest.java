package org.pepello.dto.user;

import org.pepello.common.request.BaseUpdateRequest;

import java.time.LocalDate;
import java.util.UUID;

//Kullanıcı güncelleme için parametreler
public record UserUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        UUID profilePicId,
        LocalDate birthday
) implements BaseUpdateRequest {
}
