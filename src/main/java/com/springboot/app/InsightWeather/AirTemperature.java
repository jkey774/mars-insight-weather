package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirTemperature {

    private String avgCelsius;
    private String minCelsius;
    private String maxCelsius;

    private String avgFahrenheit;
    private String minFahrenheit;
    private String maxFahrenheit;

    private DecimalFormat df = new DecimalFormat("#.#");

    public AirTemperature(ObjectNode data) {
        this.df.setRoundingMode(RoundingMode.HALF_UP);
        this.avgCelsius = data.get("av").toString();
        this.minCelsius = data.get("mn").toString();
        this.maxCelsius = data.get("mx").toString();
    }

    public String getAvgCelsius() {
        //return format(round(this.avgCelsius));
        return format(this.avgCelsius);
    }

    public void setAvgCelsius(String avgCelsius) {
        this.avgCelsius = format(avgCelsius);
    }

    public String getMinCelsius() {
        return format(this.minCelsius);
    }

    public void setMinCelsius(String minCelsius) {
        this.minCelsius = format(minCelsius);
    }

    public String getMaxCelsius() {
        return format(this.maxCelsius);
    }

    public void setMaxCelsius(String maxCelsius) {
        this.maxCelsius = format(maxCelsius);
    }

    public String getMinFahrenheit() {
        return format(celsiusToFahrenheit(this.minCelsius));
    }

    public void setMinFahrenheit() {
        this.minFahrenheit = format(celsiusToFahrenheit(this.minCelsius));
    }

    public String getAvgFahrenheit() {
        return format(celsiusToFahrenheit(this.avgCelsius));
    }

    public void setAvgFahrenheit() {
        this.avgFahrenheit = format(celsiusToFahrenheit(this.avgCelsius));
    }

    public String getMaxFahrenheit() {
        return format(celsiusToFahrenheit(this.maxCelsius));
    }

    public void setMaxFahrenheit() {
        this.avgFahrenheit = format(celsiusToFahrenheit(this.maxCelsius));
    }

    private String format(String temperature) {
        double value = Double.parseDouble(temperature);
        return df.format(value);
    }

    private static String celsiusToFahrenheit(String celsius) {
        // Formula: (0°C × 9/5) + 32 = 32°F
        double temperature = Double.parseDouble(celsius);
        return String.valueOf((temperature * (9.0 / 5.0)) + 32.0);
    }

}
