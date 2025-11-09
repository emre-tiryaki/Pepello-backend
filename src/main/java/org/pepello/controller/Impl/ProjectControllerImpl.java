package org.pepello.controller.Impl;

import org.pepello.controller.IProjectController;
import org.pepello.dto.project.DtoProject;
import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.mappers.ProjectMapper;
import org.pepello.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectControllerImpl implements IProjectController {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<DtoProject> getAll() {
        return projectService.getAll().stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Override
    public DtoProject getById(UUID id) {
        return projectMapper.toDto(projectService.getById(id));
    }

    @Override
    public DtoProject create(ProjectCreateRequest createDto) {
        return projectMapper.toDto(projectService.create(createDto));
    }

    @Override
    public DtoProject update(UUID id, ProjectUpdateRequest updateDto) {
        return projectMapper.toDto(projectService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        projectService.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return projectService.exists(id);
    }
}
