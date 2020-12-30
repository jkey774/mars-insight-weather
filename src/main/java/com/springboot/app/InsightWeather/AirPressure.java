package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirPressure {

    private String avg = "";
    private String min = "";
    private String max = "";

    private ObjectMapper objectMapper = new ObjectMapper();
    private DecimalFormat formatter1Decimal = new DecimalFormat("#.#");

    public AirPressure() {
        this.formatter1Decimal.setRoundingMode(RoundingMode.HALF_UP);
    }

    public AirPressure(ObjectNode data) {
        this();
        if (data != null) {
            if (data.get("av") != null)
                this.avg = this.formatter1Decimal.format(objectMapper.convertValue(data.get("av"), Double.class));
            if (data.get("mn") != null)
                this.min = this.formatter1Decimal.format(objectMapper.convertValue(data.get("mn"), Double.class));
            if (data.get("mx") != null)
                this.max = this.formatter1Decimal.format(objectMapper.convertValue(data.get("mx"), Double.class));
        }
    }

    public String getAvg() {
        return this.avg;
    }

    public String getMin() {
        return this.min;
    }

    public String getMax() {
        return this.max;
    }

}
