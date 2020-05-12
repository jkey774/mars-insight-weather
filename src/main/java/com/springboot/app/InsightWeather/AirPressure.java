package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirPressure {

    private String avg;
    private String min;
    private String max;

    private ObjectMapper objectMapper = new ObjectMapper();
    private DecimalFormat formatter1Decimal = new DecimalFormat("#.#");

    public AirPressure(ObjectNode data) {
        this.formatter1Decimal.setRoundingMode(RoundingMode.HALF_UP);
        this.avg = this.formatter1Decimal.format(objectMapper.convertValue(data.get("av"), Double.class));
        this.min = this.formatter1Decimal.format(objectMapper.convertValue(data.get("mn"), Double.class));
        this.max = this.formatter1Decimal.format(objectMapper.convertValue(data.get("mx"), Double.class));
    }

    public String getAvg() {
        return this.avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMin() {
        return this.min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return this.max;
    }

    public void setMax(String max) {
        this.max = max;
    }

}
