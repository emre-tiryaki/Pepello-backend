package org.pepello.controller;

import org.pepello.common.controller.ICrudEndpoints;
import org.pepello.dto.role.DtoRole;
import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;

public interface IRoleController extends ICrudEndpoints<DtoRole, RoleCreateRequest, RoleUpdateRequest> {
}
