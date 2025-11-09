package org.pepello.service.impl;

import jakarta.transaction.Transactional;
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
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role bulunamadı: " + id));
    }

    @Override
    public Role create(RoleCreateRequest createDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto == null) {
            throw new RuntimeException("CreateDto null olamaz");
        }

        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (roleRepository.existsByRoleName(createDto.name())) {
            throw new RuntimeException("Bu isimde bir role zaten mevcut");
        }

        Role newRole = Role.builder()
                .roleName(createDto.name())
                .roleDescription(createDto.description())
                .build();

        return roleRepository.save(newRole);
    }

    @Override
    public Role update(UUID id, RoleUpdateRequest updateDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (updateDto.name() != null && roleRepository.existsByRoleName(updateDto.name())) {
            throw new RuntimeException("Bu isimde bir role zaten mevcut");
        }

        Role existingRole = getById(id);

        if (updateDto.name() != null) existingRole.setRoleName(updateDto.name());
        if (updateDto.description() != null) existingRole.setRoleDescription(updateDto.description());

        return roleRepository.save(existingRole);
    }

    @Override
    public void delete(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        Role existingRole = getById(id);

        roleRepository.delete(existingRole);
    }

    @Override
    public boolean exists(UUID id) {
        return roleRepository.existsById(id);
    }
}
