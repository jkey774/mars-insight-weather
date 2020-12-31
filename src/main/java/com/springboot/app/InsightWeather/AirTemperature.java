package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.springboot.app.InsightWeather.utils.SolDataUtils.celsiusToFahrenheit;
import static com.springboot.app.InsightWeather.utils.SolDataUtils.formatNumber;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AirTemperature {

    private String min;
    private String avg;
    private String max;

    public AirTemperature() {}

    @JsonCreator
    public AirTemperature(
            @JsonProperty("mn") String min,
            @JsonProperty("av") String avg,
            @JsonProperty("mx") String max
    ) {
        this.min = min;
        this.avg = avg;
        this.max = max;
    }

    public String getMin() {
        return formatNumber(min);
    }

    public String getAvg() {
        return formatNumber(avg);
    }

    public String getMax() {
        return formatNumber(max);
    }

    public String getMinFahrenheit() {
        return formatNumber(celsiusToFahrenheit(min));
    }

    public String getAvgFahrenheit() {
        return formatNumber(celsiusToFahrenheit(avg));
    }

    public String getMaxFahrenheit() {
        return formatNumber(celsiusToFahrenheit(max));
    }
}