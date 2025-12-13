package org.pepello.dto.project;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectUpdateRequest(
        String projectName,
        String projectDescription,
        UUID icon,
        LocalDate startDate,
        LocalDate endDate
) implements BaseUpdateRequest {
}
