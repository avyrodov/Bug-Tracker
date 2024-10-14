package com.avyrodov.bugTracker.web.utils;

import com.avyrodov.bugTracker.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class RequestUtils {
    private static IUserService userService;

    public static IUserService getUserService() {
        return userService;
    }

    public static void setUserService(IUserService userService) {
        RequestUtils.userService = userService;
    }
}
