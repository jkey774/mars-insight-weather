package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;

public interface LinearSolDataInterface {
    String getMin(JsonNode data);
    String getAvg(JsonNode data);
    String getMax(JsonNode data);
}
