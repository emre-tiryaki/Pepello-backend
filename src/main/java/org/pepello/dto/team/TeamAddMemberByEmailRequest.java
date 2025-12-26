package org.pepello.dto.team;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.pepello.common.request.BaseCreateRequest;

public record TeamAddMemberByEmailRequest(
        @NotNull(message = "Email cannot be null") @NotBlank(message = "Email cannot be blank") @Email(message = "Should give valid email") String email)
        implements BaseCreateRequest {
}
