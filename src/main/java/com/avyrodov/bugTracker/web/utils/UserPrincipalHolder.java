package com.avyrodov.bugTracker.web.utils;

import com.avyrodov.bugTracker.entity.User;
import com.avyrodov.bugTracker.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

public class UserPrincipalHolder {
    public static User getUserPrincipal() {
        SecurityContext secureContext = SecurityContextHolder.getContext();
        if (secureContext != null)
            return getUserPrincipal(secureContext.getAuthentication());

        return null;
    }

    public static User getUserPrincipal(final Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof User user)
            return user;

        return null;
    }

    public static String getUserFullname() {
        User user = getUserPrincipal();

        if (user != null)
            return getUserPrincipal().getFullname();

        return null;
    }

    public static Long getUserId() {
        User user = getUserPrincipal();

        if (user != null)
            return getUserPrincipal().getUserId();

        return null;
    }
}
