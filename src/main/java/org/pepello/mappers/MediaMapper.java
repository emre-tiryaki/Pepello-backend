package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.media.DtoMedia;
import org.pepello.entities.Media;

@Mapper(componentModel = "spring")
public interface MediaMapper extends BaseMapper<Media, DtoMedia> {
}
