package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.dto.task.DtoTask;
import org.pepello.entities.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, DtoTask> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoTask toDto(Task entity);

    @Override
    @Mapping(source = "id", target = "id")
    Task fromDto(DtoTask dto);
}
