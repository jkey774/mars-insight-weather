package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sol implements Serializable {

    private String id;

    private String earthDateTimestamp;
    private EarthDate earthDate;

    private ObjectNode airTemperatureData;
    private AirTemperature airTemperature;

    private ObjectNode airPressureData;
    private AirPressure airPressure;

    private ObjectNode windSpeedData;
    private WindSpeed windSpeed;

    private ObjectNode windDirectionData;
    private WindDirection windDirection;

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

    public void setId(String id) {
        this.id = id;
    }

    public EarthDate getEarthDate() {
        return new EarthDate(this.earthDateTimestamp);
    }

    public void setEarthDate() {
        this.earthDate = new EarthDate(this.earthDateTimestamp);
    }

    public AirTemperature getAirTemperature() {
        if (airTemperatureData == null)
            return new AirTemperature();

        return new AirTemperature(airTemperatureData);

    }

    public void setAirTemperature() {
        this.airTemperature = new AirTemperature(this.airTemperatureData);
    }

    public AirPressure getAirPressure() {
        if (this.airTemperatureData == null)
            return new AirPressure();

        return new AirPressure(this.airPressureData);
    }

    public void setAirPressure() {
        this.airPressure = new AirPressure(this.airPressureData);
    }

    public WindSpeed getWindSpeed() {
        if (windSpeedData == null)
            return new WindSpeed();

        return new WindSpeed(this.windSpeedData);
    }

    public void setWindSpeed() {
        this.windSpeed = new WindSpeed(this.windSpeedData);
    }

    public WindDirection getWindDirection() {
        if (this.windDirectionData == null)
            return new WindDirection();

        return new WindDirection(this.windDirectionData);
    }

    public void setWindDirection() {
        this.windDirection = new WindDirection(this.windDirectionData);
    }

    @Override
    public String toString() {
        return "Sol{" +
                "id='" + id + '\'' +
                ", First_UTC='" + earthDateTimestamp + '\'' +
                ", earthDate=" + earthDate +
                ", atmosphereTemperature=" + airTemperature +
                '}';
    }
}
