package com.avyrodov.bugTracker.repository;

import com.avyrodov.bugTracker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE issue_id = :issueId", nativeQuery = true)
    List<Comment> getComments(Long issueId);
}
