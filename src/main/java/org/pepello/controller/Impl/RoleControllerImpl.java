package org.pepello.controller.Impl;

import org.pepello.controller.IRoleController;
import org.pepello.dto.role.DtoRole;
import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.mappers.RoleMapper;
import org.pepello.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements IRoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<DtoRole> getAll() {
        return roleService.getAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public DtoRole getById(UUID id) {
        return roleMapper.toDto(roleService.getById(id));
    }

    @Override
    public DtoRole create(RoleCreateRequest createDto) {
        return roleMapper.toDto(roleService.create(createDto));
    }

    @Override
    public DtoRole update(UUID id, RoleUpdateRequest updateDto) {
        return roleMapper.toDto(roleService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        roleService.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return roleService.exists(id);
    }
}
