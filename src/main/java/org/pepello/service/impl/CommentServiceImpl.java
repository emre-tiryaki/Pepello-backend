package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.entities.Comment;
import org.pepello.service.ICommentService;
import org.pepello.service.ITaskService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class CommentServiceImpl extends BaseCrudService<Comment, CommentCreateRequest, CommentUpdateRequest> implements ICommentService {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITaskService taskService;

    public CommentServiceImpl(JpaRepository<Comment, UUID> repository) {
        super(repository);
    }

    @Override
    protected Comment buildEntity(CommentCreateRequest createDto) {
        return Comment.builder()
                .task(taskService.getById(createDto.taskId()))
                .user(userService.getById(createDto.userId()))
                .text(createDto.text())
                .build();
    }

    @Override
    protected void updateEntity(Comment existingEntity, CommentUpdateRequest updateDto) {
        if (updateDto.taskId() != null)
            existingEntity.setTask(taskService.getById(updateDto.taskId()));
        if (updateDto.userId() != null)
            existingEntity.setUser(userService.getById(updateDto.userId()));
        if (updateDto.text() != null)
            existingEntity.setText(updateDto.text());
    }
}
