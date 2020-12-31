package com.springboot.app.InsightWeather;

import com.springboot.app.InsightWeather.*;

public interface SolInterface {
    String getId();
    EarthDate getEarthDate();
    AirTemperature getAirTemperature();
    AirPressure getAirPressure();
    WindSpeed getWindSpeed();
    WindDirection getWindDirection();
}
