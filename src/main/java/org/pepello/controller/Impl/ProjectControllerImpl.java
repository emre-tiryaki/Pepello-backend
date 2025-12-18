package org.pepello.controller.Impl;

import jakarta.validation.Valid;
import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IProjectController;
import org.pepello.dto.dashboard.DtoDashboard;
import org.pepello.dto.project.DtoProject;
import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.dto.state.DtoState;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.entities.Project;
import org.pepello.facade.DashboardFacade;
import org.pepello.mappers.DashboardMapper;
import org.pepello.mappers.StateMapper;
import org.pepello.service.impl.ProjectStateRelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectControllerImpl extends BaseCrudController<Project, DtoProject, ProjectCreateRequest, ProjectUpdateRequest> implements IProjectController {
    @Autowired
    private ProjectStateRelationServiceImpl projectStateRelationService;
    @Autowired
    private StateMapper stateMapper;
    @Autowired
    private DashboardFacade dashboardFacade;
    @Autowired
    private DashboardMapper dashboardMapper;

    public ProjectControllerImpl(ICrud<Project, ProjectCreateRequest, ProjectUpdateRequest> service, BaseMapper<Project, DtoProject> mapper) {
        super(service, mapper);
    }

    @PostMapping("/{projectId}/add-state")
    @Override
    public DtoState addStateToProject(
            @PathVariable UUID projectId,
            @Valid @RequestBody StateCreateRequest request
    ) {
        return stateMapper.toDto(projectStateRelationService.addStateToProject(projectId, request));
    }

    @GetMapping("/{projectId}/dashboard")
    @Override
    public DtoDashboard getDashboard(
            @PathVariable UUID projectId
    ) {
        return dashboardMapper.toDto(dashboardFacade.getDashboard(projectId));
    }
}
