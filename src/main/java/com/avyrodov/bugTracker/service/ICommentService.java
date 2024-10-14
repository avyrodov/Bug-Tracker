package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Comment;
import com.avyrodov.bugTracker.entity.Status;

import java.util.List;

public interface ICommentService {
    List<Comment> getComments(Long issueId);

    Comment save(Comment comment);

    void delete(Comment comment);

    Comment getComment(Long commentId);

    Comment getLastStatus(Long commentId);

    Status getLastCommentStatus(Long commentId);
}
