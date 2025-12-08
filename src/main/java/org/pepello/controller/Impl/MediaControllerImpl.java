package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IMediaController;
import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.entities.Media;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class MediaControllerImpl extends BaseCrudController<Media, DtoMedia, MediaCreateRequest, MediaUpdateRequest> implements IMediaController {
    public MediaControllerImpl(ICrud<Media, MediaCreateRequest, MediaUpdateRequest> service, BaseMapper<Media, DtoMedia> mapper) {
        super(service, mapper);
    }
}
