package com.avyrodov.bugTracker.web.utils;

import com.avyrodov.bugTracker.entity.User;
import com.avyrodov.bugTracker.service.IUserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtils {
    public static List<User> getUsers() {

        return RequestUtils.getUserService().getUsers();
    }
}
