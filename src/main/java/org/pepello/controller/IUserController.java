package org.pepello.controller;

import org.pepello.dto.user.DtoUser;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;

public interface IUserController extends ICrudEndpoints<DtoUser, UserCreateRequest, UserUpdateRequest> {
}
