package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.events.TaskAssignedEvent;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskStateChangeRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.State;
import org.pepello.entities.Task;
import org.pepello.entities.User;
import org.pepello.observers.ITaskObserver;
import org.pepello.repository.TaskRepository;
import org.pepello.service.IProjectService;
import org.pepello.service.IStateService;
import org.pepello.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskServiceImpl extends BaseCrudService<Task, TaskCreateRequest, TaskUpdateRequest> implements ITaskService {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IStateService stateService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MediaServiceImpl mediaService;
    private final TaskRepository taskRepository;

    private final List<ITaskObserver> observers = new ArrayList<>();

    public TaskServiceImpl(JpaRepository<Task, UUID> repository) {
        super(repository);
        this.taskRepository = (TaskRepository) repository;
    }

    @Override
    public Task create(TaskCreateRequest createDto) {
        Task newTask = super.create(createDto);

        if (createDto.assignee() != null)
            notifyObservers(
                    userService.getById(createDto.assignee()),
                    newTask
            );

        return newTask;
    }

    public void addObserver(ITaskObserver observer){
        this.observers.add(observer);
    }

    private void notifyObservers(User assignee, Task task){
        TaskAssignedEvent event = new TaskAssignedEvent(assignee, task);
        for (ITaskObserver observer : observers)
            observer.onAssign(event);
    }

    @Override
    protected Task buildEntity(TaskCreateRequest createDto) {
        return Task.builder()
                .project(projectService.getById(createDto.projectId()))
                .state(stateService.getById(createDto.stateId()))
                .media(mediaService.getById(createDto.media()))
                .taskTitle(createDto.taskTitle())
                .taskDescription(createDto.taskDescription())
                .isFinished(false)
                .build();
    }

    @Override
    protected void updateEntity(Task existingEntity, TaskUpdateRequest updateDto) {
        if (updateDto.projectId() != null)
            existingEntity.setProject(projectService.getById(updateDto.projectId()));
        if (updateDto.stateId() != null)
            existingEntity.setState(stateService.getById(updateDto.stateId()));
        if (updateDto.media() != null)
            existingEntity.setMedia(mediaService.getById(updateDto.media()));
        if (updateDto.assignee() != null)
            notifyObservers(userService.getById(updateDto.assignee()), existingEntity);
        if (updateDto.taskTitle() != null)
            existingEntity.setTaskTitle(updateDto.taskTitle());
        if (updateDto.taskDescription() != null)
            existingEntity.setTaskDescription(updateDto.taskDescription());
        if (updateDto.isFinished() != null)
            existingEntity.setIsFinished(updateDto.isFinished());
    }

    public Task changeTaskState(UUID taskId, TaskStateChangeRequest request) {
        //TODO: hata mimarisi!!!!
        if (request == null)
            throw new InvalidParameterException("Parameter is wrong");

        Task existingTask = getById(taskId);
        State existingState = stateService.getById(request.newStateId());

        //TODO: hata mimarisi!!!!
        if (existingTask.getState().equals(existingState))
            throw new RuntimeException("cannot change to same state");

        existingTask.setState(existingState);

        return taskRepository.save(existingTask);
    }
}
