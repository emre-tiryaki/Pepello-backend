package org.pepello.observers.impl;

import jakarta.annotation.PostConstruct;
import org.pepello.dto.events.TaskAssignedEvent;
import org.pepello.observers.ITaskObserver;
import org.pepello.service.impl.TaskAsigneeRelationServiceImpl;
import org.pepello.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskObserverImpl implements ITaskObserver {
    @Autowired
    private TaskAsigneeRelationServiceImpl taskAsigneeRelationService;
    @Autowired
    private TaskServiceImpl taskService;

    @PostConstruct
    public void registerObserver() {
        System.out.println("TaskObserver kayıt edildi");
        taskService.addObserver(this);
    }

    @Override
    public void onAssign(TaskAssignedEvent event) {
        System.out.println("Observer ile görev atama ilişkisi kuruluyor");

        UUID taskId = event.task().getId();
        UUID assigneeId = event.assignee().getId();

        if (!taskAsigneeRelationService.relationExists(taskId, assigneeId))
            taskAsigneeRelationService.addRelation(
                    taskId,
                    assigneeId
            );
    }
}
