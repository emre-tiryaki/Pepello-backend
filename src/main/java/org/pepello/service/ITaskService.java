package org.pepello.service;

import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.Task;
import org.pepello.common.ICrud;

public interface ITaskService extends ICrud<Task, TaskCreateRequest, TaskUpdateRequest> {
}
