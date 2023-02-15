package com.avtukhov.trial_project.util.voteException;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteErrorResponse {
    private String message;
    private LocalDateTime timeStamp;

    public VoteErrorResponse(String message, LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
