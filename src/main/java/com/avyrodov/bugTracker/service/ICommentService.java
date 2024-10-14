package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getComments(Long issueId);

    Comment save(Comment comment);
}
