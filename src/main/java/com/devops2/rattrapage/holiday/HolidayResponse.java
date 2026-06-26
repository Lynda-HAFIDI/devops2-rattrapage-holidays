package com.devops2.rattrapage.holiday;

public class HolidayResponse {
    private String country;
    private int year;
    private String name;
    private String date;
    private long daysRemaining;

    public HolidayResponse(String country, int year, String name, String date, long daysRemaining) {
        this.country = country;
        this.year = year;
        this.name = name;
        this.date = date;
        this.daysRemaining = daysRemaining;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }
}