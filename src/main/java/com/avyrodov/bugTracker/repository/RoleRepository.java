package com.avyrodov.bugTracker.repository;

import com.avyrodov.bugTracker.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByName(String name);
}
