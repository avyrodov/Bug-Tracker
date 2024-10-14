package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.web.issue.IssueForm;

import java.util.List;

public interface IIssueService {
    Issue getIssue(Long issueId);

    List<Issue> getIssues(IssueForm form);

    List<Issue> getIssues(List<Long> ids);

    List<Long> getIssueIds(IssueForm form);

    void deleteByIds(List<Long> ids);

    Issue save(Issue issue);
}
