package com.avyrodov.bugTracker.web.issue;

import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.entity.Status;
import com.avyrodov.bugTracker.service.IIssueService;
import com.fasterxml.jackson.databind.JsonSerializer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("issue")
public class IssueController {
    private final IIssueService issueService;

    public IssueController(IIssueService issueService) {
        this.issueService = issueService;
    }

    @ModelAttribute("command")
    public IssueForm getForm(HttpServletRequest request) {
        return new IssueForm();
    }

    @RequestMapping(value = {"/issues.html", "/"})
    public String showForm(@ModelAttribute("command") IssueForm form, Model model) {
        List<Issue> issues = issueService.getIssues(form);
        model.addAttribute("command", form);
        model.addAttribute("issues", issues);
        return "issue/issues";
    }

    @GetMapping("{issueId}/status")
    @ResponseBody
    public Status getStatus(@PathVariable(value = "issueId") Integer issueId) {
        return issueService.getStatus(issueId);
    }
}
