package org.pepello.service;

import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.User;
import org.pepello.common.ICrud;

import java.util.UUID;

public interface IUserService extends ICrud<User, UserCreateRequest, UserUpdateRequest> {
}
