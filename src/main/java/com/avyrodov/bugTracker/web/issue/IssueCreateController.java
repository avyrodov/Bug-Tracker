package com.avyrodov.bugTracker.web.issue;

import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.entity.Status;
import com.avyrodov.bugTracker.service.ICommentService;
import com.avyrodov.bugTracker.service.IIssueService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("issue")
public class IssueCreateController {
    private final IIssueService issueService;
    private final ICommentService commentService;

    public IssueCreateController(IIssueService issueService, ICommentService commentService) {
        this.issueService = issueService;
        this.commentService = commentService;
    }

    @ModelAttribute("command")
    public Issue getIssue() {
        return new Issue();
    }

    @GetMapping(value = "/create.html")
    public String showForm(@ModelAttribute("command") Issue issue, Model model, BindingResult bindingResult) {
        if (issue != null) {
            model.addAttribute("comments", commentService.getComments(issue.getIssueId()));
            model.addAttribute("command", issue);

            return "issue/create";
        } else {
            return "redirect:/issue/issues.html";
        }
    }

    @PostMapping(value = "/create.html")
    public String create(@Valid @ModelAttribute("command") Issue issue, Model model, BindingResult bindingResult) {
        if (issue != null) {
            if (bindingResult.hasErrors())
                return showForm(issue, model, bindingResult);

            if (issue.getIssueId() == null)
                issue.setStatus(Status.CREATED);

            issue.setStartDate(LocalDateTime.now());
            issueService.save(issue);
        }

        return "redirect:/issue/issues.html";
    }
}
