package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IRoleController;
import org.pepello.dto.role.DtoRole;
import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.entities.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl extends BaseCrudController<Role, DtoRole, RoleCreateRequest, RoleUpdateRequest> implements IRoleController {
    public RoleControllerImpl(ICrud<Role, RoleCreateRequest, RoleUpdateRequest> service, BaseMapper<Role, DtoRole> mapper) {
        super(service, mapper);
    }
}
