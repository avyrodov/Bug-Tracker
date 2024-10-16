package com.avyrodov.bugTracker.web.issue;

import com.avyrodov.bugTracker.entity.Comment;
import com.avyrodov.bugTracker.entity.Issue;
import com.avyrodov.bugTracker.entity.Status;
import com.avyrodov.bugTracker.service.ICommentService;
import com.avyrodov.bugTracker.service.IIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("issue")
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

    @RequestMapping(value = "/edit.html")
    public String showForm(@ModelAttribute("command") Issue issue, Model model, BindingResult bindingResult) {
        if (issue.getIssueId() == null)
            return "redirect:/issue/create.html";

        model.addAttribute("command", issue);
        model.addAttribute("comment", new Comment());

        return "issue/edit";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(required = false) Long issueId, Model model) {
        if (issueId != null) {
            Issue issue = issueService.getIssue(issueId);
            issueService.delete(issue);
        }

        return "redirect:/issue/issues.html";
    }

    @PostMapping("/comment/add")
    public String addComment(Comment comment, Model model) {
        if (comment != null) {
            comment.setUpdateDate(LocalDateTime.now());

            Issue issue = comment.getIssue();

            if (issue != null) {
                issue.getComments().add(comment);
                issue.setStatus(comment.getStatus());
                issueService.save(issue);
                model.addAttribute("command", issue);
            }
        }

        return "issue/block/comments :: comments";
    }

    @PostMapping("/comment/delete")
    public String deleteComment(@RequestParam(required = false) Long issueId,
                                @RequestParam(required = false) Long commentId,
                                Model model) {
        Issue issue = issueService.getIssue(issueId);
        Comment comment = commentService.getComment(commentId);

        commentService.delete(comment);

        Status status = commentService.getLastCommentStatus(issueId);
        issue.setStatus(status != null ? status : Status.CREATED);

        issue = issueService.save(issue);

        List<Comment> comments = commentService.getComments(issueId);
        issue.setComments(comments);

        model.addAttribute("command", issue);
        return "issue/block/comments :: comments";
    }
}
