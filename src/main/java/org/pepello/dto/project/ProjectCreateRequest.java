package org.pepello.dto.project;

import org.pepello.common.request.BaseCreateRequest;
import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectCreateRequest(
        String projectName,
        String projectDescription,
        UUID icon,
        LocalDate startDate,
        LocalDate endDate
) implements BaseCreateRequest {
}
