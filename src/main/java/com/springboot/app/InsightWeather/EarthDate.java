package com.springboot.app.InsightWeather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EarthDate {

    private String utcDateString;
    private String month;
    private String monthAbbreviated;
    private int day;
    private LocalDate dateObject;

    private static final String[] MONTHS_ABBREVIATED = {"Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};

    public EarthDate(String utcDateString) {
        this.dateObject = LocalDate.parse(utcDateString, DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getUtcDateString() {
        return utcDateString;
    }

    public String getMonth() {
        String month = String.valueOf(this.dateObject.getMonth()).toLowerCase();
        return month.substring(0, 1).toUpperCase() + month.substring(1);
    }

    public String getMonthAbbreviated() {
        return MONTHS_ABBREVIATED[this.dateObject.getMonthValue() - 1];
    }

    public int getDay() {
        return this.dateObject.getDayOfMonth();
    }

}