package org.pepello.controller;

import org.pepello.dto.project.DtoProject;
import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;

public interface IProjectController extends ICrudEndpoints<DtoProject, ProjectCreateRequest, ProjectUpdateRequest> {
}
