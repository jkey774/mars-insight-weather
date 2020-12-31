package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.Serializable;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sol implements SolInterface, Serializable {

    private ObjectMapper objectMapper;

    private String id;

    private String earthDateTimestamp;

    private ObjectNode airTemperatureData;

    private ObjectNode airPressureData;

    private ObjectNode windSpeedData;

    private ObjectNode windDirectionData;

    public Sol() {}

    @JsonCreator
    public Sol(
            @JsonProperty("id") String id,
            @JsonProperty("First_UTC") String earthDateTimestamp,
            @JsonProperty("AT") ObjectNode airTemperatureData,
            @JsonProperty("PRE") ObjectNode airPressureData,
            @JsonProperty("HWS") ObjectNode windSpeedData,
            @JsonProperty("WD") ObjectNode windDirectionData
    ) {
        this.objectMapper = new ObjectMapper();
        this.id = id;
        this.earthDateTimestamp = earthDateTimestamp;
        this.airTemperatureData = airTemperatureData;
        this.airPressureData = airPressureData;
        this.windSpeedData = windSpeedData;
        this.windDirectionData = windDirectionData;
    }

    public String getId() {
        return id;
    }

    public EarthDate getEarthDate() {
        return new EarthDate(earthDateTimestamp);
    }

    public AirTemperature getAirTemperature() {
        if (airTemperatureData == null)
            return new AirTemperature();

        return objectMapper.convertValue(airTemperatureData, AirTemperature.class);
    }

    public AirPressure getAirPressure() {
        if (airPressureData == null)
            return new AirPressure();

        return objectMapper.convertValue(airPressureData, AirPressure.class);
    }

    public WindSpeed getWindSpeed() {
        if (windSpeedData == null)
            return new WindSpeed();

        return objectMapper.convertValue(windSpeedData, WindSpeed.class);
    }

    public WindDirection getWindDirection() {
        if (windDirectionData == null)
            return new WindDirection();

        return objectMapper.convertValue(windDirectionData, WindDirection.class);
    }
}