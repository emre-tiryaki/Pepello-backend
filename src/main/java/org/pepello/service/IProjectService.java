package org.pepello.service;

import org.pepello.dto.project.ProjectCreateRequest;
import org.pepello.dto.project.ProjectUpdateRequest;
import org.pepello.entities.Project;

public interface IProjectService extends ICrudService<Project, ProjectCreateRequest, ProjectUpdateRequest> {
}
