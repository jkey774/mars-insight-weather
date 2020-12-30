package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WindDirection {

    private JsonNode summaryData;
    private String compassPoint = "";

    public WindDirection() {}

    public WindDirection(ObjectNode data) {
        if (data != null) {
            this.summaryData = data.get("most_common");
            if (this.summaryData != null && this.summaryData.get("compass_point") != null) {
                this.compassPoint = this.summaryData.get("compass_point").toString().replace("\"", "");
            }
        }
    }

    public String getCompassPoint() {
        return this.compassPoint;
    }

}