package org.pepello.service.impl;

import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.entities.Project;
import org.pepello.repository.ProjectRepository;
import org.pepello.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(UUID id) {
        //TODO: hata fırlat!!!!!!
        return projectRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Project create(ProjectCreateRequest createDto) {
        //TODO: hata fırlat!!!!
        if (createDto == null)
            return null;

        Project newProject = Project.builder()
                .projectName(createDto.projectName())
                .projectDescription(createDto.projectDescription())
                //TODO: buraya mantık ekle
                .icon(null)
                .startDate(createDto.startDate())
                .endDate(createDto.endDate())
                .build();

        return projectRepository.save(newProject);
    }

    @Override
    public Project update(UUID id, ProjectUpdateRequest updateDto) {
        //TODO:hata fırlat!!!
        if (id == null || updateDto == null)
            return null;

        Project existingProject = getById(id);

        if (updateDto.projectName() != null) existingProject.setProjectName(updateDto.projectName());
        if (updateDto.projectDescription() != null)
            existingProject.setProjectDescription(updateDto.projectDescription());
        //TODO: buraya mantık ekle
        if (updateDto.icon() != null) existingProject.setIcon(null);
        if (updateDto.startDate() != null) existingProject.setStartDate(updateDto.startDate());
        if (updateDto.endDate() != null) existingProject.setEndDate(updateDto.endDate());

        return projectRepository.save(existingProject);
    }

    @Override
    public void delete(UUID id) {
        //TODO: hata!!!!!!!
        if (id == null)
            return;

        Project existingProject = getById(id);

        projectRepository.delete(existingProject);
    }
}
