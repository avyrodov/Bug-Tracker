package com.avyrodov.bugTracker.web.issue;

import com.avyrodov.bugTracker.entity.Comment;
import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.service.ICommentService;
import com.avyrodov.bugTracker.service.IIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class IssueEditController {
    private final IIssueService issueService;
    private final ICommentService commentService;

    public IssueEditController(IIssueService issueService, ICommentService commentService) {
        this.issueService = issueService;
        this.commentService = commentService;
    }

    @ModelAttribute("command")
    public Issue getForm(@RequestParam(required = false) Long issueId) {
        Issue issue = null;

        if (issueId != null)
            issue = issueService.getIssue(issueId);

        if (issue == null)
            issue = new Issue();

        return issue;
    }

    @RequestMapping(value = "/issue/edit.html")
    public String showForm(@ModelAttribute("command") Issue issue, Model model, BindingResult bindingResult) {
        if (issue.getIssueId() == null)
            return "redirect:/issue/create.html";

        model.addAttribute("command", issue);
        model.addAttribute("comment", new Comment());

        return "issue/edit";
    }

    @PostMapping("/issue/comment/add")
    public String addComment(Comment comment, Model model) {
        if(comment != null) {
            comment.setUpdateDate(LocalDateTime.now());

            Issue issue = comment.getIssue();

            if(issue != null) {
                issue.getComments().add(comment);
                issue.setStatus(comment.getStatus());
                issueService.save(issue);
                model.addAttribute("command", issue);
            }
        }

        return "issue/block/comments :: comments";
    }
}
