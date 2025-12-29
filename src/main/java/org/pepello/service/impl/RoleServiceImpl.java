package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.role.RoleCreateRequest;
import org.pepello.dto.role.RoleUpdateRequest;
import org.pepello.entities.Role;
import org.pepello.repository.RoleRepository;
import org.pepello.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.pepello.common.exception.business.BusinessException;


import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl extends BaseCrudService<Role, RoleCreateRequest, RoleUpdateRequest> implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl(JpaRepository<Role, UUID> repository) {
        super(repository);
    }

    @Override
    protected Role buildEntity(RoleCreateRequest createDto) {
        // Role name uniqueness kontrolü
        if (roleRepository.existsByRoleName(createDto.name())) {
            throw new BusinessException("Bu isimde bir role zaten mevcut");
        }

        return Role.builder()
                .roleName(createDto.name())
                .roleDescription(createDto.description())
                .build();
    }

    @Override
    protected void updateEntity(Role existingEntity, RoleUpdateRequest updateDto) {
        if (updateDto.name() != null) {
            // Update sırasında da name uniqueness kontrolü
            if (!existingEntity.getRoleName().equals(updateDto.name()) &&
                    roleRepository.existsByRoleName(updateDto.name())) {
                throw new BusinessException("Bu isimde bir role zaten mevcut");
            }
            existingEntity.setRoleName(updateDto.name());
        }
        if (updateDto.description() != null)
            existingEntity.setRoleDescription(updateDto.description());
    }
}
