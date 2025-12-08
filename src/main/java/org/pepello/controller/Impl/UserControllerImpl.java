package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IUserController;
import org.pepello.dto.user.DtoUser;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerImpl extends BaseCrudController<User, DtoUser, UserCreateRequest, UserUpdateRequest> implements IUserController {
    public UserControllerImpl(ICrud<User, UserCreateRequest, UserUpdateRequest> service, BaseMapper<User, DtoUser> mapper) {
        super(service, mapper);
    }
}
