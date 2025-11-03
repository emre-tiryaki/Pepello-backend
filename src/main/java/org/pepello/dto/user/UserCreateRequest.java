package org.pepello.dto.user;

import java.time.LocalDate;

//Kullanıcı yaratma için parametreler
public record UserCreateRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String profilePicUrl,
        LocalDate birthday
) {
}
