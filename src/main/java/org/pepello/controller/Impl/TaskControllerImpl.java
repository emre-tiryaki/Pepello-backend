package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.ITaskController;
import org.pepello.dto.task.*;
import org.pepello.entities.Task;
import org.pepello.mappers.TaskMapper;
import org.pepello.service.ITaskAsigneeRelationService;
import org.pepello.service.ITaskService;
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
    public DtoTask changeTaskState(
            @PathVariable UUID taskId,
            @RequestBody TaskStateChangeRequest request
    ){
        return taskMapper.toDto(taskService.changeTaskState(taskId, request));
    }

    @PostMapping("/{taskId}/assign")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignToTask(
            @PathVariable UUID taskId,
            @RequestBody AssignToTaskRequest request
    ){
        taskAsigneeRelationService.addRelation(taskId, request.assignee());
    }
}
