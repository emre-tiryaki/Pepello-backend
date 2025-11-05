package org.pepello.service;

import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.Task;

public interface ITaskService extends ICrudService<Task, TaskCreateRequest, TaskUpdateRequest> {
}
