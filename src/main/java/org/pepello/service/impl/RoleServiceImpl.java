package org.pepello.service.impl;

import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.entities.Role;
import org.pepello.repository.RoleRepository;
import org.pepello.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(UUID id) {
        //TODO: hata mimarisini yap
        return roleRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Role create(RoleCreateRequest createDto) {
        //TODO: hata fırlat!!!
        if (createDto == null)
            return null;

        //TODO: hata fırlat!!!
        if (roleRepository.existsByRoleName(createDto.name()))
            return null;

        Role newRole = Role.builder()
                .roleName(createDto.name())
                .roleDescription(createDto.description())
                .build();

        return roleRepository.save(newRole);
    }

    @Override
    public Role update(UUID id, RoleUpdateRequest updateDto) {
        //TODO: hata fırlat!!!
        if (id == null || updateDto == null)
            return null;

        //TODO: hata fırlat!!!
        if (updateDto.name() != null && roleRepository.existsByRoleName(updateDto.name()))
            return null;

        Role existingRole = getById(id);

        if (updateDto.name() != null) existingRole.setRoleName(updateDto.name());
        if (updateDto.description() != null) existingRole.setRoleDescription(updateDto.description());

        return roleRepository.save(existingRole);
    }

    @Override
    public void delete(UUID id) {
        //TODO: hata fırlat nolur
        if (id == null)
            return;

        Role existingRole = getById(id);

        roleRepository.delete(existingRole);
    }
}
