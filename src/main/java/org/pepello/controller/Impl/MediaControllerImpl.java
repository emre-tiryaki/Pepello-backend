package org.pepello.controller.Impl;

import org.pepello.controller.IMediaController;
import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.mappers.MediaMapper;
import org.pepello.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/media")
public class MediaControllerImpl implements IMediaController {
    @Autowired
    private IMediaService mediaService;
    @Autowired
    private MediaMapper mediaMapper;


    @Override
    public List<DtoMedia> getAll() {
        return mediaService.getAll().stream()
                .map(mediaMapper::toDto)
                .toList();
    }

    @Override
    public DtoMedia getById(UUID id) {
        return mediaMapper.toDto(mediaService.getById(id));
    }

    @Override
    public DtoMedia create(MediaCreateRequest createDto) {
        return mediaMapper.toDto(mediaService.create(createDto));
    }

    @Override
    public DtoMedia update(UUID id, MediaUpdateRequest updateDto) {
        return mediaMapper.toDto(mediaService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        mediaService.delete(id);
    }
}
