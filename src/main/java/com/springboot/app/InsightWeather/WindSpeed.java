package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.springboot.app.InsightWeather.utils.SolDataUtils.metersPerSecondToMilesPerHour;
import static com.springboot.app.InsightWeather.utils.SolDataUtils.formatNumber;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WindSpeed {

    private String avg;
    private String min;
    private String max;

    public WindSpeed() {}

    @JsonCreator
    public WindSpeed(
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

    public String getMinMilesPerHour() {
        return formatNumber(metersPerSecondToMilesPerHour(min));
    }

    public String getAvgMilesPerHour() {
        return formatNumber(metersPerSecondToMilesPerHour(avg));
    }

    public String getMaxMilesPerHour() {
        return formatNumber(metersPerSecondToMilesPerHour(max));
    }
}