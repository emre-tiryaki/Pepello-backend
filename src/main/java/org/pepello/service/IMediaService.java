package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;
import org.pepello.entities.Media;

public interface IMediaService extends ICrud<Media, MediaCreateRequest, MediaUpdateRequest> {
}
