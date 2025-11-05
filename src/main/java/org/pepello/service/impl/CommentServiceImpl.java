package org.pepello.service.impl;

import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.entities.Comment;
import org.pepello.entities.Task;
import org.pepello.entities.User;
import org.pepello.repository.CommentRepository;
import org.pepello.service.ICommentService;
import org.pepello.service.ITaskService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITaskService taskService;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(UUID id) {
        //TODO: hata fırlat!!!!
        if (id == null)
            return null;

        return commentRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Comment create(CommentCreateRequest createDto) {
        //TODO: hata
        if (createDto == null)
            return null;

        Comment newComment = new Comment();

        Task existingTask = taskService.getById(createDto.taskId());
        newComment.setTask(existingTask);

        User existingUser = userService.getById(createDto.userId());
        newComment.setUser(existingUser);

        newComment.setText(createDto.text());

        return commentRepository.save(newComment);
    }

    @Override
    public Comment update(UUID id, CommentUpdateRequest updateDto) {
        //TODO: hata
        if (id == null || updateDto == null)
            return null;

        Comment existingComment = getById(id);

        if (updateDto.taskId() != null) {
            Task existingTask = taskService.getById(updateDto.taskId());
            existingComment.setTask(existingTask);
        }
        if (updateDto.userId() != null) {
            User existingUser = userService.getById(updateDto.userId());
            existingComment.setUser(existingUser);
        }
        if (updateDto.text() != null) existingComment.setText(updateDto.text());

        return commentRepository.save(existingComment);
    }

    @Override
    public void delete(UUID id) {
        //TODO: allah aşkına hata fırlat
        if (id == null)
            return;

        Comment existingComment = getById(id);

        commentRepository.delete(existingComment);
    }
}
