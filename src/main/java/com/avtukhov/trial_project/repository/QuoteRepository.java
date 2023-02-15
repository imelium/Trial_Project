package com.avtukhov.trial_project.repository;

import com.avtukhov.trial_project.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

}
