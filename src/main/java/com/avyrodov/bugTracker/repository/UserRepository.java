package com.avyrodov.bugTracker.repository;

import com.avyrodov.bugTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM _user WHERE user_id = :userId", nativeQuery = true)
    User getUser(Long userId);
}
