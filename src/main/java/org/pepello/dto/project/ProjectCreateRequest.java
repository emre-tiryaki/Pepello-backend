package org.pepello.dto.project;

import org.pepello.common.request.BaseCreateRequest;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectCreateRequest(
        UUID teamId,
        String projectName,
        String projectDescription,
        UUID icon,
        LocalDate startDate,
        LocalDate endDate
) implements BaseCreateRequest {
}
