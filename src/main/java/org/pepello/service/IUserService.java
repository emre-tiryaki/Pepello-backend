package org.pepello.service;

import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.User;

public interface IUserService extends ICrudService<User, UserCreateRequest, UserUpdateRequest> {
}
