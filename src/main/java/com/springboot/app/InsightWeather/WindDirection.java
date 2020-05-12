package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WindDirection {

    private JsonNode summaryData;
    private String compassPoint;

    public WindDirection(ObjectNode data) {
        this.summaryData = data.get("most_common");
        this.compassPoint = this.summaryData.get("compass_point").toString().replace("\"", "");
    }

    public String getCompassPoint() {
        return this.compassPoint;
    }

    public void setCompassPoint(String compassPoint) {
        this.compassPoint = compassPoint;
    }

}
