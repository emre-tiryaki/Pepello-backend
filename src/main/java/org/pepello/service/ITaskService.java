package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskStateChangeRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.entities.Project;
import org.pepello.entities.Task;

import java.util.List;
import java.util.UUID;

public interface ITaskService extends ICrud<Task, TaskCreateRequest, TaskUpdateRequest> {
    Task changeTaskState(UUID taskId, TaskStateChangeRequest request);

    void changeCompletion(UUID taskId, boolean completion);

    void attachMediaToTask(UUID taskId, UUID mediaId);

    List<Task> getByProject(Project project);
}
