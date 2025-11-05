package org.pepello.service;

import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.entities.Role;

public interface IRoleService extends ICrudService<Role, RoleCreateRequest, RoleUpdateRequest> {
}
