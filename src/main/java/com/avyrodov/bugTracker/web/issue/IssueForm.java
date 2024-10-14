package com.avyrodov.bugTracker.web.issue;

import com.avyrodov.bugTracker.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IssueForm {
    private List<Long> authorIds;
    private List<Long> authorCommentIds;
    private String name;
    private Status status;
}
