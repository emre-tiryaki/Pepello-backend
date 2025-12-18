package org.pepello.facade;

import org.pepello.dto.dashboard.Dashboard;
import org.pepello.entities.Project;
import org.pepello.entities.State;
import org.pepello.entities.Task;
import org.pepello.service.impl.ProjectServiceImpl;
import org.pepello.service.impl.ProjectStateRelationServiceImpl;
import org.pepello.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DashboardFacade {
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ProjectStateRelationServiceImpl projectStateRelationService;
    @Autowired
    private TaskServiceImpl taskService;
    
    public Dashboard getDashboard(UUID projectId) {
        Project existingProject = projectService.getById(projectId);

        List<State> states = projectStateRelationService.getRelatedEntities(projectId);
        List<Task> tasks = taskService.getByProject(existingProject);

        return Dashboard.builder()
                .states(states)
                .tasks(tasks)
                .build();
    }
}
