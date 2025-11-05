package org.pepello.controller.Impl;

import org.pepello.controller.IUserController;
import org.pepello.dto.user.DtoUser;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.mappers.UserMapper;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<DtoUser> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public DtoUser getById(UUID id) {
        return userMapper.toDto(userService.getById(id));
    }

    @Override
    public DtoUser create(UserCreateRequest createDto) {
        return userMapper.toDto(userService.create(createDto));
    }

    @Override
    public DtoUser update(UUID id, UserUpdateRequest updateDto) {
        return userMapper.toDto(userService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        userService.delete(id);
    }
}
