package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.User;
import com.avyrodov.bugTracker.web.login.RegistrationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User findByUsername(String username);

    User getUser(Long userId);

    User save(RegistrationForm form);

    List<User> getUsers();
}
