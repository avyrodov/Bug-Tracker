package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.entity.Status;
import com.avyrodov.bugTracker.repository.IssueRepository;
import com.avyrodov.bugTracker.web.issue.IssueForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService implements IIssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Issue getIssue(Long issueId) {
        return issueRepository.getIssue(issueId);
    }

    @Override
    public List<Issue> getIssues(IssueForm form) {
        List<Long> ids = getIssueIds(form);
        return issueRepository.getIssues(ids);
    }

    @Override
    public List<Issue> getIssues(List<Long> ids) {
        return issueRepository.getIssues(ids);
    }

    @Override
    public List<Long> getIssueIds(IssueForm form) {
        final String status = form.getStatus() != null ? form.getStatus().name() : "";
        String name = form.getName() != null ? form.getName().toLowerCase().toLowerCase() : "";
        return issueRepository.getIssueIds(form.getAuthorIds(), form.getAuthorCommentIds(), name, status);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        issueRepository.deleteByIds(ids);
    }

    @Override
    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public void delete(Issue issue) {
        issueRepository.delete(issue);
    }

    @Override
    public Status getStatus(Integer issueId) {
        return issueRepository.getStatus(issueId);
    }
}

