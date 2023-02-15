package com.avtukhov.trial_project.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDTO {
    private String quoteDesc;
    private UserDTO owner;

}
