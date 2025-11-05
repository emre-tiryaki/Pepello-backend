package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.dto.project.DtoProject;
import org.pepello.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<Project, DtoProject> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoProject toDto(Project entity);

    @Override
    @Mapping(source = "id", target = "id")
    Project fromDto(DtoProject dto);
}
