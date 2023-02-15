package com.avtukhov.trial_project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date_of_create")
    private LocalDateTime dateOdVote;
    @Column(name = "score")
    @NotNull
    private int score;
    @ManyToOne
    @JoinColumn(name = "quote_vote", referencedColumnName = "id")
    private Quote quote_vote;
    @ManyToOne
    @JoinColumn(name = "user_vote", referencedColumnName = "id")
    private User1 user1;
}
