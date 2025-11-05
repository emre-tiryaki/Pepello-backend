package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.dto.state.DtoState;
import org.pepello.entities.State;

@Mapper(componentModel = "spring")
public interface StateMapper extends BaseMapper<State, DtoState> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoState toDto(State entity);

    @Override
    @Mapping(source = "id", target = "id")
    State fromDto(DtoState dto);
}
