package org.pepello.service;

import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.entities.Comment;

public interface ICommentService extends ICrudService<Comment, CommentCreateRequest, CommentUpdateRequest> {
}
