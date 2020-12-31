package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.util.StringUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LinearSolData {

    private DecimalFormat df = new DecimalFormat("#.#");

    public LinearSolData() {
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    public final String getMin(JsonNode data) {
        return getProperty(data, "mn");
    }

    public final String getAvg(JsonNode data) {
        return getProperty(data, "av");
    }

    public final String getMax(JsonNode data) {
        return getProperty(data, "mx");
    }

    private String getProperty(JsonNode data, String property) {
        if (data == null || !StringUtils.hasLength(property))
            return "";

        JsonNode node = data.get(property);

        if (node == null)
            return "";

        String value;

        try {
            value = df.format(Double.parseDouble(node.textValue()));
        } catch (Exception e) {
            e.printStackTrace();
            value = node.textValue();
        }

        return value;
    }

}
