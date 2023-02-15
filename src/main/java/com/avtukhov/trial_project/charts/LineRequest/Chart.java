package com.avtukhov.trial_project.charts.LineRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "type",
        "data"
})
@Getter
@Setter
public class Chart {

    @JsonProperty("type")
    public String type = "line";
    @JsonProperty("data")
    public Data data;

    public Chart() {
    }

    public Chart(Data data) {
        this.data = data;
    }
}