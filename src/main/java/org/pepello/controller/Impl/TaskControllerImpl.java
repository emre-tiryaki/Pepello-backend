package org.pepello.controller.Impl;

import org.pepello.controller.ITaskController;
import org.pepello.dto.task.DtoTask;
import org.pepello.dto.task.TaskCreateRequest;
import org.pepello.dto.task.TaskUpdateRequest;
import org.pepello.mappers.TaskMapper;
import org.pepello.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskControllerImpl implements ITaskController {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<DtoTask> getAll() {
        return taskService.getAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public DtoTask getById(UUID id) {
        return taskMapper.toDto(taskService.getById(id));
    }

    @Override
    public DtoTask create(TaskCreateRequest createDto) {
        return taskMapper.toDto(taskService.create(createDto));
    }

    @Override
    public DtoTask update(UUID id, TaskUpdateRequest updateDto) {
        return taskMapper.toDto(taskService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        taskService.delete(id);
    }
}
