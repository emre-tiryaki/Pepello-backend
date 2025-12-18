package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserSearchRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.User;
import org.pepello.repository.UserRepository;
import org.pepello.service.IMediaService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends BaseCrudService<User, UserCreateRequest, UserUpdateRequest> implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMediaService mediaService;
    @Autowired
    private PasswordServiceImpl passwordService;

    public UserServiceImpl(JpaRepository<User, UUID> repository) {
        super(repository);
    }

    @Override
    protected User buildEntity(UserCreateRequest createDto) {
        // Validation
        if (createDto.email() == null || createDto.password() == null ||
                createDto.firstName() == null || createDto.lastName() == null) {
            throw new RuntimeException("Gerekli alanlar eksik");
        }

        if (userRepository.existsByEmail(createDto.email())) {
            throw new RuntimeException("Bu email zaten kullanılıyor");
        }

        return User.builder()
                .firstName(createDto.firstName())
                .lastName(createDto.lastName())
                .email(createDto.email())
                .password(passwordService.hash(createDto.password()))
                .profilePic(createDto.profilePicId() == null ? null : mediaService.getById(createDto.profilePicId()))
                .birthday(createDto.birthday())
                .build();
    }

    @Override
    protected void updateEntity(User existingEntity, UserUpdateRequest updateDto) {
        if (updateDto.firstName() != null)
            existingEntity.setFirstName(updateDto.firstName());
        if (updateDto.lastName() != null)
            existingEntity.setLastName(updateDto.lastName());
        if (updateDto.email() != null) {
            if (!existingEntity.getEmail().equals(updateDto.email()) &&
                    userRepository.existsByEmail(updateDto.email())) {
                throw new RuntimeException("Bu email zaten kullanılıyor");
            }
            existingEntity.setEmail(updateDto.email());
        }
        if (updateDto.password() != null)
            existingEntity.setPassword(passwordService.hash(updateDto.password()));
        if (updateDto.profilePicId() != null)
            existingEntity.setProfilePic(mediaService.getById(updateDto.profilePicId()));
        if (updateDto.birthday() != null)
            existingEntity.setBirthday(updateDto.birthday());
    }

    public List<User> searchUser(UserSearchRequest request) {
        if (request == null)
            throw new IllegalArgumentException("request cannot be null");

        List<User> userList = new ArrayList<>();

        if (request.name() != null)
            userList.addAll(userRepository.findByFullName(request.name().trim()));

        return userList;
    }
}
