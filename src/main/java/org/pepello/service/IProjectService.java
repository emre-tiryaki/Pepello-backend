package org.pepello.service;

import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.entities.Project;
import org.pepello.common.ICrud;

public interface IProjectService extends ICrud<Project, ProjectCreateRequest, ProjectUpdateRequest> {
}
