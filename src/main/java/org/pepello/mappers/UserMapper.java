package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.user.DtoUser;
import org.pepello.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, DtoUser> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoUser toDto(User entity);

    @Override
    @Mapping(source = "id", target = "id")
    User fromDto(DtoUser dto);
}
