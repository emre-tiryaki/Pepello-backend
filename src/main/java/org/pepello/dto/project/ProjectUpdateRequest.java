package org.pepello.dto.project;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;

public record ProjectUpdateRequest(
        String projectName,
        String projectDescription,
        DtoMedia icon,
        LocalDate startDate,
        LocalDate endDate
) implements BaseUpdateRequest {
}
