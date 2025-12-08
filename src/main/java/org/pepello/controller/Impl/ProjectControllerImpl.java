package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IProjectController;
import org.pepello.dto.project.DtoProject;
import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.entities.Project;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectControllerImpl extends BaseCrudController<Project, DtoProject, ProjectCreateRequest, ProjectUpdateRequest> implements IProjectController {
    public ProjectControllerImpl(ICrud<Project, ProjectCreateRequest, ProjectUpdateRequest> service, BaseMapper<Project, DtoProject> mapper) {
        super(service, mapper);
    }
}
