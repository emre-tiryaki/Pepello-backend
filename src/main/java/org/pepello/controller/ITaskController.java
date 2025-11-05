package org.pepello.controller;

import org.pepello.dto.task.DtoTask;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;

public interface ITaskController extends ICrudEndpoints<DtoTask, TaskCreateRequest, TaskUpdateRequest> {

}
