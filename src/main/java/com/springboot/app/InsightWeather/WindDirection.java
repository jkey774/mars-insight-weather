package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import static com.springboot.app.InsightWeather.utils.SolDataUtils.UI_NULL_VALUE;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WindDirection {

    private JsonNode summaryData;

    public WindDirection() {}

    @JsonCreator
    public WindDirection(
            @JsonProperty("most_common") JsonNode summaryData
    ) {
        this.summaryData = summaryData;
    }

    public String getCompassPoint() {
        if (summaryData != null && summaryData.get("compass_point") != null)
            return summaryData.get("compass_point").textValue();

        return UI_NULL_VALUE;
    }

}