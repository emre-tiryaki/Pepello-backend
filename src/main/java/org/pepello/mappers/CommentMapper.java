package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.comment.DtoComment;
import org.pepello.entities.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<Comment, DtoComment> {
}
