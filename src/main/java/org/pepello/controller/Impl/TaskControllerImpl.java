package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.ITaskController;
import org.pepello.dto.task.DtoTask;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskControllerImpl extends BaseCrudController<Task, DtoTask, TaskCreateRequest, TaskUpdateRequest> implements ITaskController {
    public TaskControllerImpl(ICrud<Task, TaskCreateRequest, TaskUpdateRequest> service, BaseMapper<Task, DtoTask> mapper) {
        super(service, mapper);
    }
}
