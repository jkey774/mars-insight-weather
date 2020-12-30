package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.StringUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WindSpeed {

    private String avgMetersPerSecond = "";
    private String minMetersPerSecond = "";
    private String maxMetersPerSecond = "";

    private String avgMilesPerHour = "";
    private String minMilesPerHour = "";
    private String maxMilesPerHour = "";

    private ObjectMapper objectMapper = new ObjectMapper();
    private DecimalFormat df = new DecimalFormat("#.#");

    public WindSpeed() {
        this.df.setRoundingMode(RoundingMode.HALF_UP);
    }

    public WindSpeed(ObjectNode data) {
        this();
        if (data != null) {
            if (data.get("av") != null)
                this.avgMetersPerSecond = this.df.format(objectMapper.convertValue(data.get("av"), Double.class));
            if (data.get("mn") != null)
                this.minMetersPerSecond = this.df.format(objectMapper.convertValue(data.get("mn"), Double.class));
            if (data.get("mx") != null)
                this.maxMetersPerSecond = this.df.format(objectMapper.convertValue(data.get("mx"), Double.class));
        }
    }

    public String getAvgMetersPerSecond() {
        return format(this.avgMetersPerSecond);
    }

    public void setAvgMetersPerSecond(String avgMetersPerSecond) {
        this.avgMetersPerSecond = format(avgMetersPerSecond);
    }

    public String getMinMetersPerSecond() {
        return format(this.minMetersPerSecond);
    }

    public void setMinMetersPerSecond(String minMetersPerSecond) {
        this.minMetersPerSecond = format(minMetersPerSecond);
    }

    public String getMaxMetersPerSecond() {
        return format(this.maxMetersPerSecond);
    }

    public void setMaxMetersPerSecond(String maxMetersPerSecond) {
        this.maxMetersPerSecond = format(maxMetersPerSecond);
    }

    public String getAvgMilesPerHour() {
        return format(metersPerSecondToMilesPerHour(this.avgMetersPerSecond));
    }

    public void setAvgMilesPerHour() {
        this.avgMilesPerHour = format(metersPerSecondToMilesPerHour(this.avgMetersPerSecond));
    }

    public String getMinMilesPerHour() {
        return format(metersPerSecondToMilesPerHour(this.minMetersPerSecond));
    }

    public void setMinMilesPerHour() {
        this.minMilesPerHour = format(metersPerSecondToMilesPerHour(this.minMetersPerSecond));
    }

    public String getMaxMilesPerHour() {
        return format(metersPerSecondToMilesPerHour(this.maxMetersPerSecond));
    }

    public void setMaxMilesPerHour() {
        this.maxMilesPerHour = format(metersPerSecondToMilesPerHour(this.maxMetersPerSecond));
    }

    private String format(String speed) {
        if (!StringUtils.hasLength(speed))
            return "";

        double value = Double.parseDouble(speed);
        return df.format(value);
    }

    private static String metersPerSecondToMilesPerHour(String metersPerSecond) {
        if (!StringUtils.hasLength(metersPerSecond))
            return "";

        // Formula: multiply mps by 2.237
        double speed = Double.parseDouble(metersPerSecond);
        return String.valueOf(speed * 2.237);
    }

}
