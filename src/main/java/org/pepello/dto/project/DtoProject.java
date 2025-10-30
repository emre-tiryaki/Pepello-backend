package org.pepello.dto.project;

import org.pepello.dto.media.DtoMedia;

import java.time.LocalDate;
import java.util.UUID;

public record DtoProject(
        UUID projectId,
        String projectName,
        String projectDescription,
        DtoMedia icon,
        LocalDate startDate,
        LocalDate endDate
) {
}
