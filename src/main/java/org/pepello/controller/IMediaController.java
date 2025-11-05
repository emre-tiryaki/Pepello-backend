package org.pepello.controller;

import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.media.MediaCreateRequest;
import org.pepello.dto.media.MediaUpdateRequest;

public interface IMediaController extends ICrudEndpoints<DtoMedia, MediaCreateRequest, MediaUpdateRequest> {
}
