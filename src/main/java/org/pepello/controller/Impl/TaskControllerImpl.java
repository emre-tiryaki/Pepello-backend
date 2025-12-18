package org.pepello.controller.Impl;

import jakarta.validation.Valid;
import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.ITaskController;
import org.pepello.dto.task.*;
import org.pepello.entities.Task;
import org.pepello.mappers.TaskMapper;
import org.pepello.service.impl.TaskAsigneeRelationServiceImpl;
import org.pepello.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskControllerImpl extends BaseCrudController<Task, DtoTask, TaskCreateRequest, TaskUpdateRequest> implements ITaskController {
    private final TaskServiceImpl taskService;
    private final TaskMapper taskMapper;
    @Autowired
    private TaskAsigneeRelationServiceImpl taskAsigneeRelationService;

    public TaskControllerImpl(ICrud<Task, TaskCreateRequest, TaskUpdateRequest> service, BaseMapper<Task, DtoTask> mapper) {
        super(service, mapper);
        this.taskService = (TaskServiceImpl) service;
        this.taskMapper = (TaskMapper) mapper;
    }

    @PatchMapping("/{taskId}/move")
    @Override
    public DtoTask changeTaskState(
            @PathVariable UUID taskId,
            @Valid @RequestBody TaskStateChangeRequest request
    ) {
        return taskMapper.toDto(taskService.changeTaskState(taskId, request));
    }

    @PostMapping("/{taskId}/assign")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void assignToTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody AssignToTaskRequest request
    ) {
        taskAsigneeRelationService.addRelation(taskId, request.assignee());
    }

    @DeleteMapping("/{taskId}/assign/{userId}")
    @Override
    public void deAssignFromTask(
            @PathVariable UUID taskId,
            @PathVariable UUID userId
    ) {
        taskAsigneeRelationService.removeRelation(taskId, userId);
    }

    @PatchMapping("/{taskId}/changeCompletion")
    @Override
    public void completeTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody TaskCompletionRequest request
    ) {
        taskService.changeCompletion(taskId, request.completion());
    }

    @PostMapping("/{taskId}/attachment")
    @Override
    public void attachMediaToTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody AttachMediaRequest request
    ) {
        taskService.attachMediaToTask(taskId, request.mediaId());
    }
}
