package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.project.DtoProject;
import org.pepello.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<Project, DtoProject> {
}
