package org.pepello.observers.impl;

import jakarta.annotation.PostConstruct;
import org.pepello.dto.events.ProjectCreatedEvent;
import org.pepello.entities.State;
import org.pepello.entities.Task;
import org.pepello.observers.IProjectObserver;
import org.pepello.service.impl.ProjectServiceImpl;
import org.pepello.service.impl.ProjectStateRelationServiceImpl;
import org.pepello.service.impl.StateServiceImpl;
import org.pepello.service.impl.TaskServiceImpl;
import org.pepello.service.template.ProjectTemplateRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectObserverImpl implements IProjectObserver {
    @Autowired
    private ProjectTemplateRegistry templateRegistry;
    @Autowired
    private StateServiceImpl stateService;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ProjectStateRelationServiceImpl projectStateRelationService;
    @Autowired
    private TaskServiceImpl taskService;

    @PostConstruct
    public void registerObserver() {
        System.out.println("ProjectObserver kayıt edildi");
        projectService.addObserver(this);
    }

    @Override
    public void onProjectCreated(ProjectCreatedEvent event) {

        List<State> states = templateRegistry.getDefaultStates();
        List<Task> tasks = templateRegistry.getDefaultTasks();

        State firstState = null;

        for (State state : states) {
            State savedState = stateService.create(state);

            if (firstState == null) firstState = savedState;

            projectStateRelationService.addRelation(
                    event.createdProject().getId(),
                    savedState.getId()
            );
        }

        if (firstState != null) {
            for (Task task : tasks) {
                task.setProject(event.createdProject());
                task.setState(firstState);
                taskService.create(task);
            }
        }
    }
}
