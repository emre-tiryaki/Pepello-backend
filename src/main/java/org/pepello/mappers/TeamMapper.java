package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.dto.team.DtoTeam;
import org.pepello.entities.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper extends BaseMapper<Team, DtoTeam> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoTeam toDto(Team entity);

    @Override
    @Mapping(source = "id", target = "id")
    Team fromDto(DtoTeam dto);
}
