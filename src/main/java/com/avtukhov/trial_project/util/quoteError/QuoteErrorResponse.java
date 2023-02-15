package com.avtukhov.trial_project.util.quoteError;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class QuoteErrorResponse {

    private String message;
    private LocalDateTime timeStamp;

    public QuoteErrorResponse(String message, LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
