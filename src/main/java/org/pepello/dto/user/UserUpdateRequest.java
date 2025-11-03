package org.pepello.dto.user;

import java.time.LocalDate;

//Kullanıcı güncelleme için parametreler
public record UserUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String profilePicUrl,
        LocalDate birthday
) {
}
