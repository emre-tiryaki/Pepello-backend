package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.state.DtoState;
import org.pepello.entities.State;

@Mapper(componentModel = "spring")
public interface StateMapper extends BaseMapper<State, DtoState> {
}
