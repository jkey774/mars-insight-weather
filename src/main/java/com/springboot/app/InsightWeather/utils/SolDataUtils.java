package com.springboot.app.InsightWeather.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SolDataUtils {

    public static final String UI_NULL_VALUE = "n/a";

    private static final DecimalFormat df = new DecimalFormat("#.#");

    public static String celsiusToFahrenheit(String celsius) {
        if (celsius == null)
            return UI_NULL_VALUE;

        String fahrenheit;

        // Formula: (0°C × 9/5) + 32 = 32°F
        try {
            double temperature = Double.parseDouble(celsius);
            fahrenheit = String.valueOf((temperature * (9.0 / 5.0)) + 32.0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            fahrenheit = UI_NULL_VALUE;
        }

        return fahrenheit;
    }

    public static String metersPerSecondToMilesPerHour(String metersPerSecond) {
        if (metersPerSecond == null)
            return UI_NULL_VALUE;

        String milesPerHour;

        // Formula: MetersPerSecond * 2.237
        try {
            double speed = Double.parseDouble(metersPerSecond);
            milesPerHour = String.valueOf(speed * 2.237);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            milesPerHour = UI_NULL_VALUE;
        }

        return milesPerHour;

    }

    public static String formatNumber(String numberStr) {
        String formattedNumber = UI_NULL_VALUE;

        if (numberStr == null)
            return formattedNumber;

        df.setRoundingMode(RoundingMode.HALF_UP);

        try {
            Double number = Double.parseDouble(numberStr);
            formattedNumber = df.format(number);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedNumber;
    }
}
