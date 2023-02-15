package com.avtukhov.trial_project.charts.LineRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({
        "labels",
        "datasets"
})
@Getter
@Setter
public class Data {

    @JsonProperty("labels")
    public List<Integer> labels;
    @JsonProperty("datasets")
    public List<Dataset> datasets;

    public Data() {
    }
}