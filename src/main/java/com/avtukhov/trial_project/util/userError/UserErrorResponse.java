package com.avtukhov.trial_project.util.userError;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserErrorResponse {

    private String message;
    private LocalDateTime timeStamp;

    public UserErrorResponse(String message, LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
