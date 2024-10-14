package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Comment;
import com.avyrodov.bugTracker.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getComments(Long issueId) {
        return commentRepository.getComments(issueId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
