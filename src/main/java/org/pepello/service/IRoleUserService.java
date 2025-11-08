package org.pepello.service;

import org.pepello.common.service.RelationMethods;
import org.pepello.entities.Role;
import org.pepello.entities.RoleUserRelation;
import org.pepello.entities.User;

public interface IRoleUserService extends RelationMethods<Role, User, RoleUserRelation> {
}
