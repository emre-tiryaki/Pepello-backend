package org.pepello.service.impl;

import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.Project;
import org.pepello.entities.State;
import org.pepello.entities.Task;
import org.pepello.repository.TaskRepository;
import org.pepello.service.IProjectService;
import org.pepello.service.IStateService;
import org.pepello.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IStateService stateService;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Task create(TaskCreateRequest createDto) {
        //TODO: aaaaaaa
        if (createDto == null)
            return null;

        //TODO: hata var!!!
        Task newTask = Task.builder()
                .project(projectService.getById(createDto.projectId()))
                .state(stateService.getById(createDto.stateId()))
                //TODO:buraya mantık ekle
                .media(null)
                .taskTitle(createDto.taskTitle())
                .taskDescription(createDto.taskDescription())
                .isFinished(false)
                .build();

        return taskRepository.save(newTask);
    }

    @Override
    public Task update(UUID id, TaskUpdateRequest updateDto) {
        //TODO: hataaa
        if (id == null || updateDto == null)
            return null;

        Task existingTask = getById(id);

        if (updateDto.projectId() != null) {
            Project existingProject = projectService.getById(updateDto.projectId());
            existingTask.setProject(existingProject);
        }
        if (updateDto.stateId() != null) {
            State existingState = stateService.getById(updateDto.stateId());
            existingTask.setState(existingState);
        }
        //TODO: buraya mantık ekle
        if (updateDto.media() != null) existingTask.setMedia(null);
        if (updateDto.taskTitle() != null) existingTask.setTaskTitle(updateDto.taskTitle());
        if (updateDto.taskDescription() != null) existingTask.setTaskDescription(updateDto.taskDescription());
        if (updateDto.isFinished() != null) existingTask.setIsFinished(updateDto.isFinished());

        return taskRepository.save(existingTask);
    }

    @Override
    public void delete(UUID id) {
        //TODO: hata fırlaaaaaaat!!!!!!!
        if (id == null)
            return;

        Task existingTask = getById(id);

        taskRepository.delete(existingTask);
    }
}
