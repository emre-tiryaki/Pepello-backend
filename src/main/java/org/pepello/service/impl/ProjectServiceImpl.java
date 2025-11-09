package org.pepello.service.impl;

import jakarta.transaction.Transactional;
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
@Transactional
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project bulunamadı: " + id));
    }

    @Override
    public Project create(ProjectCreateRequest createDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto == null) {
            throw new RuntimeException("CreateDto null olamaz");
        }

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
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

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
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        Project existingProject = getById(id);

        projectRepository.delete(existingProject);
    }

    @Override
    public boolean exists(UUID id) {
        return projectRepository.existsById(id);
    }
}
