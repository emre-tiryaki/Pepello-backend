package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.Media;
import org.pepello.entities.User;
import org.pepello.repository.UserRepository;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordServiceImpl passwordService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User bulunamadı: " + id));
    }

    @Override
    public User create(UserCreateRequest createDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto == null) {
            throw new RuntimeException("CreateDto null olamaz");
        }

        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto.email() == null || createDto.password() == null ||
                createDto.firstName() == null || createDto.lastName() == null) {
            throw new RuntimeException("Gerekli alanlar eksik");
        }

        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (userRepository.existsByEmail(createDto.email())) {
            throw new RuntimeException("Bu email zaten kullanılıyor");
        }

        User newUser = User.builder()
                .firstName(createDto.firstName())
                .lastName(createDto.lastName())
                .email(createDto.email())
                .password(passwordService.hash(createDto.password()))
                .birthday(createDto.birthday())
                //TODO: buraya mantık ekle
                .profilePic(null)
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public User update(UUID id, UserUpdateRequest updateDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

        User existingUser = getById(id);

        if (updateDto.firstName() != null) existingUser.setFirstName(updateDto.firstName());
        if (updateDto.lastName() != null) existingUser.setLastName(updateDto.lastName());
        if (updateDto.email() != null) existingUser.setEmail(updateDto.email());
        if (updateDto.password() != null) existingUser.setPassword(passwordService.hash(updateDto.password()));
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
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        User existingUser = getById(id);

        userRepository.delete(existingUser);
    }

    @Override
    public boolean exists(UUID id) {
        return userRepository.existsById(id);
    }
}
