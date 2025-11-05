package org.pepello.service;

import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.entities.Media;

public interface IMediaService extends ICrudService<Media, MediaCreateRequest, MediaUpdateRequest> {
}
