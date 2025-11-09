package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.team.DtoTeam;
import org.pepello.entities.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper extends BaseMapper<Team, DtoTeam> {
}
