package org.pepello.controller;

import org.pepello.dto.user.DtoUser;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.mappers.UserMapper;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController implements ICrudEndpoints<DtoUser, UserCreateRequest, UserUpdateRequest>{
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<List<DtoUser>> getAll() {
        return ResponseEntity.ok().body(
                userService.getAll().stream()
                        .map(userMapper::toDto)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<DtoUser> getById(UUID id) {
        return ResponseEntity.ok().body(
                userMapper.toDto(userService.getById(id))
        );
    }

    @Override
    public ResponseEntity<DtoUser> create(UserCreateRequest createDto) {
        return ResponseEntity.ok().body(
                userMapper.toDto(userService.create(createDto))
        );
    }

    @Override
    public ResponseEntity<DtoUser> update(UUID id, UserUpdateRequest updateDto) {
        return ResponseEntity.ok().body(
                userMapper.toDto(userService.update(id, updateDto))
        );
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
