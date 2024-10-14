package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Comment;
import com.avyrodov.bugTracker.entity.Status;
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

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment getComment(Long commentId) {
        return commentRepository.getComment(commentId);
    }

    @Override
    public Comment getLastStatus(Long commentId) {
        return commentRepository.getLastComment(commentId);
    }

    @Override
    public Status getLastCommentStatus(Long commentId) {
        return commentRepository.getLastCommentStatus(commentId);
    }
}
