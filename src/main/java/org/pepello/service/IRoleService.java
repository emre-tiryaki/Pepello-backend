package org.pepello.service;

import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.entities.Role;
import org.pepello.common.ICrud;

public interface IRoleService extends ICrud<Role, RoleCreateRequest, RoleUpdateRequest> {
}
