package com.avtukhov.trial_project.repository;

import com.avtukhov.trial_project.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
