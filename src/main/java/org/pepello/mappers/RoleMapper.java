package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.dto.role.DtoRole;
import org.pepello.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, DtoRole> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoRole toDto(Role entity);

    @Override
    @Mapping(source = "id", target = "id")
    Role fromDto(DtoRole dto);
}
