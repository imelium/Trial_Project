package com.avtukhov.trial_project.charts.LineRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonPropertyOrder({
        "label",
        "data"
})
@Getter
@Setter
public class Dataset {

    @JsonProperty("label")
    public String label;
    @JsonProperty("data")
    public List<Integer> data;

    public Dataset() {
    }
}