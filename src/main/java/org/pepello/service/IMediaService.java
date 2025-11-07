package org.pepello.service;

import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.entities.Media;
import org.pepello.common.ICrud;

public interface IMediaService extends ICrud<Media, MediaCreateRequest, MediaUpdateRequest> {
}
