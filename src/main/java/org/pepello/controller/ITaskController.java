package org.pepello.controller;

import org.pepello.dto.task.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface ITaskController {

    @PatchMapping("/{taskId}/move")
    DtoTask changeTaskState(
            @PathVariable UUID taskId,
            @RequestBody TaskStateChangeRequest request
    );

    @PostMapping("/{taskId}/assign")
    @ResponseStatus(HttpStatus.CREATED)
    void assignToTask(
            @PathVariable UUID taskId,
            @RequestBody AssignToTaskRequest request
    );

    @DeleteMapping("/{taskId}/assign/{userId}")
    void deAssignFromTask(
            @PathVariable UUID taskId,
            @PathVariable UUID userId
    );

    @PatchMapping("/{taskId}/changeCompletion")
    void completeTask(
            @PathVariable UUID taskId,
            @RequestBody TaskCompletionRequest request
    );

    @PostMapping("/{taskId}/attachment")
    void attachMediaToTask(
            @PathVariable UUID taskId,
            @RequestBody AttachMediaRequest request
    );
}
