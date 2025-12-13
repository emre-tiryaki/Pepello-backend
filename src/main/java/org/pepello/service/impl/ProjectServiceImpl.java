package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.entities.Project;
import org.pepello.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ProjectServiceImpl extends BaseCrudService<Project, ProjectCreateRequest, ProjectUpdateRequest> implements IProjectService {

    @Autowired
    private MediaServiceImpl mediaService;

    public ProjectServiceImpl(JpaRepository<Project, UUID> repository) {
        super(repository);
    }

    @Override
    protected Project buildEntity(ProjectCreateRequest createDto) {
        return Project.builder()
                .projectName(createDto.projectName())
                .projectDescription(createDto.projectDescription())
                .icon(mediaService.getById(createDto.icon()))
                .startDate(createDto.startDate())
                .endDate(createDto.endDate())
                .build();
    }

    @Override
    protected void updateEntity(Project existingEntity, ProjectUpdateRequest updateDto) {
        if (updateDto.projectName() != null)
            existingEntity.setProjectName(updateDto.projectName());
        if (updateDto.projectDescription() != null)
            existingEntity.setProjectDescription(updateDto.projectDescription());
        if (updateDto.icon() != null)
            existingEntity.setIcon(mediaService.getById(updateDto.icon()));
        if (updateDto.startDate() != null)
            existingEntity.setStartDate(updateDto.startDate());
        if (updateDto.endDate() != null)
            existingEntity.setEndDate(updateDto.endDate());
    }
}
