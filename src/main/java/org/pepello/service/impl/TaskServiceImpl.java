package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.observer.Observerable;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.events.TaskAssignedEvent;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskStateChangeRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.*;
import org.pepello.observers.ITaskObserver;
import org.pepello.common.observer.ObserverManager;
import org.pepello.repository.TaskRepository;
import org.pepello.service.IProjectService;
import org.pepello.service.IStateService;
import org.pepello.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.pepello.common.exception.business.BusinessException;
import org.pepello.common.exception.validation.ValidationException;


import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskServiceImpl extends BaseCrudService<Task, TaskCreateRequest, TaskUpdateRequest> implements ITaskService, Observerable<ITaskObserver> {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IStateService stateService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MediaServiceImpl mediaService;
    private final TaskRepository taskRepository;

    private final ObserverManager<ITaskObserver> observerManager = new ObserverManager<>();

    public TaskServiceImpl(JpaRepository<Task, UUID> repository) {
        super(repository);
        this.taskRepository = (TaskRepository) repository;
    }

    @Override
    public void addObserver(ITaskObserver observer) {
        observerManager.addObserver(observer);
    }

    private void notifyObservers(User assignee, Task task) {
        TaskAssignedEvent event = new TaskAssignedEvent(assignee, task);
        for (ITaskObserver observer : observerManager.getObservers()) {
            observer.onAssign(event);
        }
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

    @Override
    protected Task buildEntity(TaskCreateRequest createDto) {
        return Task.builder()
                .project(projectService.getById(createDto.projectId()))
                .state(stateService.getById(createDto.stateId()))
                .media(createDto.media() != null ? mediaService.getById(createDto.media()) : null)
                .taskTitle(createDto.taskTitle())
                .taskDescription(createDto.taskDescription())
                .isFinished(false)
                .build();
    }

    @Override
    protected void updateEntity(Task existingEntity, TaskUpdateRequest updateDto) {
        if (updateDto.projectId() != null) {
            Project project = projectService.getById(updateDto.projectId());
            existingEntity.setProject(project);
        }
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

    @Override
    public Task changeTaskState(UUID taskId, TaskStateChangeRequest request) {
        //TODO: hata mimarisi!!!!
        if (request == null)
            throw new ValidationException("Parameter is wrong");

        Task existingTask = getById(taskId);
        State existingState = stateService.getById(request.newStateId());

        //TODO: hata mimarisi!!!!
        if (existingTask.getState().equals(existingState))
            throw new BusinessException("cannot change to same state");

        existingTask.setState(existingState);

        return repository.save(existingTask);
    }

    @Override
    public void changeCompletion(UUID taskId, boolean completion) {
        if (taskId == null)
            throw new ValidationException("Task id can not be null");

        Task existingTask = getById(taskId);

        existingTask.setIsFinished(completion);

        repository.save(existingTask);
    }

    @Override
    public void attachMediaToTask(UUID taskId, UUID mediaId) {
        if (mediaId == null)
            throw new ValidationException("media can not be null");

        Media mediaToAttach = mediaService.getById(mediaId);
        Task task = getById(taskId);

        task.setMedia(mediaToAttach);

        repository.save(task);
    }

    @Override
    public List<Task> getByProject(Project project) {
        return taskRepository.findByProject(project);
    }
}
