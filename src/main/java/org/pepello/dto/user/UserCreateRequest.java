package org.pepello.dto.user;

import org.pepello.common.request.BaseCreateRequest;

import java.time.LocalDate;

//Kullanıcı yaratma için parametreler
public record UserCreateRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String profilePicUrl,
        LocalDate birthday
) implements BaseCreateRequest {
}
