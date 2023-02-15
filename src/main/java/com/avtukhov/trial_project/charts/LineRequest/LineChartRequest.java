package com.avtukhov.trial_project.charts.LineRequest;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "backgroundColor",
        "width",
        "height",
        "devicePixelRatio",
        "chart"
})
@Getter
@Setter
public class LineChartRequest {

    @JsonProperty("backgroundColor")
    public String backgroundColor = "#fff";
    @JsonProperty("width")
    public Integer width = 500;
    @JsonProperty("height")
    public Integer height = 300;
    @JsonProperty("chart")
    public Chart chart;


    public LineChartRequest(Chart chart) {
        this.chart = chart;
    }

    public LineChartRequest() {
    }
}