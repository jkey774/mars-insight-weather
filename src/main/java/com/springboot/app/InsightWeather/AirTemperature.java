package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.StringUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirTemperature {

    private String avgCelsius = "";
    private String minCelsius = "";
    private String maxCelsius = "";

    private String avgFahrenheit = "";
    private String minFahrenheit = "";
    private String maxFahrenheit = "";

    private DecimalFormat df = new DecimalFormat("#.#");

    public AirTemperature() {
        this.df.setRoundingMode(RoundingMode.HALF_UP);
    }

    public AirTemperature(ObjectNode data) {
        this();
        if (data != null) {
            if (data.get("av") != null)
                this.avgCelsius = data.get("av").toString();
            if (data.get("mn") != null)
                this.minCelsius = data.get("mn").toString();
            if (data.get("mx") != null)
                this.maxCelsius = data.get("mx").toString();
        }
    }

    public String getAvgCelsius() {
        return format(this.avgCelsius);
    }

    public String getMinCelsius() {
        return format(this.minCelsius);
    }

    public String getMaxCelsius() {
        return format(this.maxCelsius);
    }

    public String getMinFahrenheit() {
        return format(celsiusToFahrenheit(this.minCelsius));
    }

    public String getAvgFahrenheit() {
        return format(celsiusToFahrenheit(this.avgCelsius));
    }

    public String getMaxFahrenheit() {
        return format(celsiusToFahrenheit(this.maxCelsius));
    }

    private String format(String temperature) {
        if (!StringUtils.hasLength(temperature))
            return "";

        double value = Double.parseDouble(temperature);
        return df.format(value);
    }

    private static String celsiusToFahrenheit(String celsius) {
        if (!StringUtils.hasLength(celsius))
            return "";

        // Formula: (0°C × 9/5) + 32 = 32°F
        double temperature = Double.parseDouble(celsius);
        return String.valueOf((temperature * (9.0 / 5.0)) + 32.0);
    }

}
