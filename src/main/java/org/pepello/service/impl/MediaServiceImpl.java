package org.pepello.service.impl;

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
public class MediaServiceImpl implements IMediaService {
    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public List<Media> getAll() {
        return mediaRepository.findAll();
    }

    @Override
    public Media getById(UUID id) {
        //TODO: hataa!!
        if (id == null)
            return null;

        //TODO: hataa!!
        return mediaRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Media create(MediaCreateRequest createDto) {
        //TODO: hataa!!
        if (createDto == null)
            return null;

        Media newMedia = Media.builder()
                .mediaUrl(createDto.mediaUrl())
                .mediaType(createDto.mediaType())
                .mediaSize(createDto.mediaSize())
                .build();

        return mediaRepository.save(newMedia);
    }

    @Override
    public Media update(UUID id, MediaUpdateRequest updateDto) {
        //TODO: hataa!!
        if (id == null || updateDto == null)
            return null;

        Media existingMedia = getById(id);

        if (updateDto.mediaUrl() != null) existingMedia.setMediaUrl(updateDto.mediaUrl());
        if (updateDto.mediaType() != null) existingMedia.setMediaType(updateDto.mediaType());
        if (updateDto.mediaSize() != null) existingMedia.setMediaSize(updateDto.mediaSize());

        return mediaRepository.save(existingMedia);
    }

    @Override
    public void delete(UUID id) {
        //TODO: hataa!!
        if (id == null)
            return;

        Media existingMedia = getById(id);

        mediaRepository.delete(existingMedia);
    }
}
