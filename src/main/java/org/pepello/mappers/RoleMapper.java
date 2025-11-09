package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.role.DtoRole;
import org.pepello.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, DtoRole> {
}
