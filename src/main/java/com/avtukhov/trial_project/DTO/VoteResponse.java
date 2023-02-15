package com.avtukhov.trial_project.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteResponse {
    private int score;
    private LocalDateTime dateOdVote;

}
