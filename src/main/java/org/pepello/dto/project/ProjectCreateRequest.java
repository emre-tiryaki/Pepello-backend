package org.pepello.dto.project;

import org.pepello.common.request.BaseCreateRequest;
import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;

public record ProjectCreateRequest(
        String projectName,
        String projectDescription,
        DtoMedia icon,
        LocalDate startDate,
        LocalDate endDate
) implements BaseCreateRequest {
}
