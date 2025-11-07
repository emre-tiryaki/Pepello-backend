package org.pepello.service;

import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.User;
import org.pepello.common.ICrud;

public interface IUserService extends ICrud<User, UserCreateRequest, UserUpdateRequest> {
}
