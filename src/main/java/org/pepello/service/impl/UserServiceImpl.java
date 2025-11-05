package org.pepello.service.impl;

import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.Media;
import org.pepello.entities.User;
import org.pepello.entities.enums.MediaType;
import org.pepello.repository.MediaRepository;
import org.pepello.repository.UserRepository;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordServiceImpl passwordService;
    //TODO: burayı daha profesyonel yazabilirsin. güveniyorum sana ilerideki ben :)
    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(UUID id) {
        // TODO: exception mimarisi yap ve hata fırlat
        if (id == null)
            return null;

        //TODO: bulunamazsa hata fırlatmalı
        return userRepository.findById(id)
                .orElseGet(() -> null);
    }

    @Override
    public User create(UserCreateRequest createDto) {
        if (createDto == null)
            return null;

        if (createDto.email() == null || createDto.password() == null || createDto.firstName() == null || createDto.lastName() == null)
            return null;

        //TODO: hata fırlat
        if (userRepository.existsByEmail(createDto.email()))
            return null;

        User newUser = new User();
        newUser.setFirstName(createDto.firstName());
        newUser.setLastName(createDto.lastName());
        newUser.setEmail(createDto.email());
        newUser.setPassword(passwordService.hash(createDto.password()));
        newUser.setBirthday(createDto.birthday());

        Media profilePic = new Media();

        //TODO: media servisi yapınca burayı düzelt
        profilePic.setMediaUrl(createDto.profilePicUrl() != null ? createDto.profilePicUrl() : "https://thumbs.dreamstime.com/b/default-avatar-profile-trendy-style-social-media-user-icon-187599373.jpg");
        profilePic.setMediaType(MediaType.image);
        //TODO: burası 0 olmicak
        profilePic.setMediaSize(BigDecimal.ZERO);
        mediaRepository.save(profilePic);

        newUser.setProfilePic(profilePic);

        return userRepository.save(newUser);
    }

    @Override
    public User update(UUID id, UserUpdateRequest updateDto) {
        if (id == null || updateDto == null)
            return null;

        //TODO: hatayı düzelt buradaki
        User existingUser = getById(id);

        if (updateDto.firstName() != null) existingUser.setFirstName(updateDto.firstName());
        if (updateDto.lastName() != null) existingUser.setLastName(updateDto.lastName());
        if (updateDto.email() != null) existingUser.setEmail(updateDto.email());
        if (updateDto.password() != null) existingUser.setPassword(updateDto.password());
        if (updateDto.profilePicUrl() != null) {
            //TODO: Media servisini implemente et
            Media profilePic = existingUser.getProfilePic();

            profilePic.setMediaUrl(updateDto.profilePicUrl());
            //TODO: burası 0 olmicak
            profilePic.setMediaSize(BigDecimal.ZERO);

            existingUser.setProfilePic(profilePic);
        }
        if (updateDto.birthday() != null) existingUser.setBirthday(updateDto.birthday());

        return userRepository.save(existingUser);
    }

    @Override
    public void delete(UUID id) {
        //TODO: hata fırlatcak bura
        if (id == null)
            return;

        //TODO: buradaki hata fırlatsın
        User existingUser = getById(id);

        userRepository.delete(existingUser);
    }
}
