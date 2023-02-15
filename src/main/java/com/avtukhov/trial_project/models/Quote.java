package com.avtukhov.trial_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "quote_desc")
    private String quoteDesc;
    @Column(name = "date_of_creat")
    private LocalDateTime timeOfCreation;
    @Column(name = "date_of_last_edit")
    private LocalDateTime timeOfLastEdit;
    @Column(name = "score")
    private int score;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User1 owner;
    @OneToMany(mappedBy = "quote_vote", fetch = FetchType.EAGER)
    private List<Vote> totalVotes;

    public Quote() {
    }


}
