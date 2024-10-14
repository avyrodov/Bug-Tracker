package com.avyrodov.bugTracker.service;

import com.avyrodov.bugTracker.entity.Role;
import com.avyrodov.bugTracker.entity.User;
import com.avyrodov.bugTracker.repository.RoleRepository;
import com.avyrodov.bugTracker.repository.UserRepository;
import com.avyrodov.bugTracker.web.login.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Role not present"));
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public User save(RegistrationForm form) {
        String role = "ROLE_USER";
        Role userRole = roleRepository.getRoleByName(role).orElseThrow(() -> new NoSuchElementException("Role not present"));

        User user = new User();
        user.setFirstname(form.getFirstname());
        user.setSurname(form.getSurname());
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(userRole);

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
