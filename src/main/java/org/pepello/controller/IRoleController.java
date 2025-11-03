package org.pepello.controller;

import org.pepello.dto.role.DtoRole;
import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.service.ICrudService;

public interface IRoleController extends ICrudService<DtoRole, RoleCreateRequest, RoleUpdateRequest> {
}
