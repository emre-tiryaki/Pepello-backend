package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.entities.Project;

public interface IProjectService extends ICrud<Project, ProjectCreateRequest, ProjectUpdateRequest> {
}
