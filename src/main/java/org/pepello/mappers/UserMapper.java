package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.dto.user.DtoUser;
import org.pepello.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, DtoUser> {
}
