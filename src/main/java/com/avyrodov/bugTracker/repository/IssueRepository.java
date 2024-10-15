package com.avyrodov.bugTracker.repository;

import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.entity.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query(value = "SELECT * FROM issue WHERE issue_id = :issueId", nativeQuery = true)
    Issue getIssue(Long issueId);

    @Query(value = "SELECT * FROM issue WHERE issue_id in (:ids)", nativeQuery = true)
    List<Issue> getIssues(List<Long> ids);

    @Query(value = "SELECT i.issue_id FROM issue i " +
            "LEFT JOIN _user u ON i.user_id = u.user_id " +
            "LEFT JOIN comment c ON i.user_id = c.user_id " +
            "WHERE (u.user_id IN (:authorIds) OR :authorIds IS NULL) " +
            "and (c.user_id IN (:authorCommentIds) OR :authorCommentIds IS NULL) " +
            "and (LOWER(i.name) LIKE (%:name%)) " +
            "and (i.status = :status OR :status IS NULL OR :status = '')", nativeQuery = true)
    List<Long> getIssueIds(List<Long> authorIds, List<Long> authorCommentIds, String name, String status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM issue WHERE issue_id in (:ids)", nativeQuery = true)
    void deleteByIds(List<Long> ids);

    Issue save(Issue Issue);

    @Query(value = "SELECT status FROM issue WHERE issue_id = :issueId", nativeQuery = true)
    Status getStatus(Integer issueId);
}
