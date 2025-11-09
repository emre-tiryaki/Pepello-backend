package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.entities.Media;
import org.pepello.repository.MediaRepository;
import org.pepello.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MediaServiceImpl implements IMediaService {
    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public List<Media> getAll() {
        return mediaRepository.findAll();
    }

    @Override
    public Media getById(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        return mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media bulunamadı: " + id));
    }

    @Override
    public Media create(MediaCreateRequest createDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto == null) {
            throw new RuntimeException("CreateDto null olamaz");
        }

        Media newMedia = Media.builder()
                .mediaUrl(createDto.mediaUrl())
                .mediaType(createDto.mediaType())
                .mediaSize(createDto.mediaSize())
                .build();

        return mediaRepository.save(newMedia);
    }

    @Override
    public Media update(UUID id, MediaUpdateRequest updateDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

        Media existingMedia = getById(id);

        if (updateDto.mediaUrl() != null) existingMedia.setMediaUrl(updateDto.mediaUrl());
        if (updateDto.mediaType() != null) existingMedia.setMediaType(updateDto.mediaType());
        if (updateDto.mediaSize() != null) existingMedia.setMediaSize(updateDto.mediaSize());

        return mediaRepository.save(existingMedia);
    }

    @Override
    public void delete(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        Media existingMedia = getById(id);

        mediaRepository.delete(existingMedia);
    }

    @Override
    public boolean exists(UUID id) {
        return mediaRepository.existsById(id);
    }
}
