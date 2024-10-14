package com.avyrodov.bugTracker.web.issue;

import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.service.IIssueService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IssueController {
    private final IIssueService issueService;

    public IssueController(IIssueService issueService) {
        this.issueService = issueService;
    }

    @ModelAttribute("command")
    public IssueForm getForm(HttpServletRequest request) {
        return new IssueForm();
    }

    @RequestMapping(value = {"/issue/issues.html", "/"})
    public String showForm(@ModelAttribute("command") IssueForm form, Model model) {
        List<Issue> issues = issueService.getIssues(form);
        model.addAttribute("command", form);
        model.addAttribute("issues", issues);
        return "issue/issues";
    }
}
