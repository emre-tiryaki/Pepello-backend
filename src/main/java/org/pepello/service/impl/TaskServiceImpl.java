package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.Task;
import org.pepello.service.IProjectService;
import org.pepello.service.IStateService;
import org.pepello.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class TaskServiceImpl extends BaseCrudService<Task, TaskCreateRequest, TaskUpdateRequest> implements ITaskService {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IStateService stateService;

    public TaskServiceImpl(JpaRepository<Task, UUID> repository) {
        super(repository);
    }

    @Override
    protected Task buildEntity(TaskCreateRequest createDto) {
        return Task.builder()
                .project(projectService.getById(createDto.projectId()))
                .state(stateService.getById(createDto.stateId()))
                .media(null) // media şimdilik null - DTO'da DtoMedia var
                .taskTitle(createDto.taskTitle())
                .taskDescription(createDto.taskDescription())
                .build();
    }

    @Override
    protected void updateEntity(Task existingEntity, TaskUpdateRequest updateDto) {
        if (updateDto.projectId() != null)
            existingEntity.setProject(projectService.getById(updateDto.projectId()));
        if (updateDto.stateId() != null)
            existingEntity.setState(stateService.getById(updateDto.stateId()));
        if (updateDto.media() != null)
            existingEntity.setMedia(null); // media update şimdilik null
        if (updateDto.taskTitle() != null)
            existingEntity.setTaskTitle(updateDto.taskTitle());
        if (updateDto.taskDescription() != null)
            existingEntity.setTaskDescription(updateDto.taskDescription());
        if (updateDto.isFinished() != null)
            existingEntity.setIsFinished(updateDto.isFinished());
    }
}
