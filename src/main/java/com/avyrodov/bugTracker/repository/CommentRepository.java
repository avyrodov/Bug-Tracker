package com.avyrodov.bugTracker.repository;

import com.avyrodov.bugTracker.entity.Comment;
import com.avyrodov.bugTracker.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE issue_id = :issueId", nativeQuery = true)
    List<Comment> getComments(Long issueId);

    @Query(value = "SELECT * FROM comment WHERE comment_id = :commentId", nativeQuery = true)
    Comment getComment(Long commentId);

    @Query(value = "SELECT * FROM comment ORDER BY comment_id DESC LIMIT 1", nativeQuery = true)
    Comment getLastComment(Long issueId);

    @Query(value = "SELECT status FROM comment WHERE issue_id = :issueId ORDER BY comment_id DESC LIMIT 1", nativeQuery = true)
    Status getLastCommentStatus(Long issueId);
}
