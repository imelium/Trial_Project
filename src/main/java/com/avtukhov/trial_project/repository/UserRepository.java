package com.avtukhov.trial_project.repository;

import com.avtukhov.trial_project.models.User1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User1, Integer> {
    Optional<User1> findByUserName(String name);
}
