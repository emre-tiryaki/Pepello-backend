package org.pepello.service;

import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.entities.Comment;
import org.pepello.common.ICrud;

public interface ICommentService extends ICrud<Comment, CommentCreateRequest, CommentUpdateRequest> {
}
