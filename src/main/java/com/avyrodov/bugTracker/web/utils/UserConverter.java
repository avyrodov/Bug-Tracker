//package com.avyrodov.bugTracker.web.utils;
//
//import com.avyrodov.bugTracker.entity.User;
//import com.avyrodov.bugTracker.service.IUserService;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserConverter implements Converter<Long, User> {
//    private final IUserService userService;
//
//    public UserConverter(IUserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public User convert(Long issueId) {
//        return userService.getUser(issueId);
//    }
//}
