package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.entities.Media;
import org.pepello.service.IMediaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class MediaServiceImpl extends BaseCrudService<Media, MediaCreateRequest, MediaUpdateRequest> implements IMediaService {

    public MediaServiceImpl(JpaRepository<Media, UUID> repository) {
        super(repository);
    }

    @Override
    protected Media buildEntity(MediaCreateRequest createDto) {
        return Media.builder()
                .mediaUrl(createDto.mediaUrl())
                .mediaType(createDto.mediaType())
                .mediaSize(createDto.mediaSize())
                .build();
    }

    @Override
    protected void updateEntity(Media existingEntity, MediaUpdateRequest updateDto) {
        if (updateDto.mediaUrl() != null)
            existingEntity.setMediaUrl(updateDto.mediaUrl());
        if (updateDto.mediaType() != null)
            existingEntity.setMediaType(updateDto.mediaType());
        if (updateDto.mediaSize() != null)
            existingEntity.setMediaSize(updateDto.mediaSize());
    }
}
