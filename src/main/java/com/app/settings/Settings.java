package com.app.settings;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Settings {
    private Units.Temperature temperature;
    private Units.Pressure pressure;
    private String city;
    private boolean isWaitingForCity;
    private boolean isWaitingForZonedDateTime;
    private boolean language;
    private ZonedDateTime zonedDateTime;

    public Settings() {
        this.temperature = Units.Temperature.CELSIUS;
        this.pressure = Units.Pressure.MILLIMETERS;
        this.city = null;
        this.isWaitingForCity = true;
        this.isWaitingForZonedDateTime = false;
        this.language = true;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public Units.Temperature getTemperature() {
        return this.temperature;
    }

    public Units.Pressure getPressure() {
        return this.pressure;
    }

    public String getCity() {
        return this.city;
    }

    public boolean isWaitingForCity() {
        return this.isWaitingForCity;
    }

    public boolean getLanguage() {
        return this.language;
    }

    public ZonedDateTime getZonedDateTime() {
        return this.zonedDateTime;
    }

    public boolean isWaitingForZonedDateTime() {
        return this.isWaitingForZonedDateTime;
    }

    public void setTemperature(Units.Temperature temperature) {
        this.temperature = temperature;
    }

    public void setPressure(Units.Pressure pressure) {
        this.pressure = pressure;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setWaitingForCity(boolean isWaitingForCity) {
        this.isWaitingForCity = isWaitingForCity;
    }

    public void setLanguage(boolean language) {
        this.language = language;
    }

    public void setZonedDateTime(int hoursOffset, int minutesOffset) {
        this.zonedDateTime = ZonedDateTime.now(ZoneId.ofOffset("UTC",
                ZoneOffset.ofHoursMinutes(hoursOffset, minutesOffset)));
    }

    public void setWaitingForZonedDateTime(boolean isWaitingForZonedDateTime) {
        this.isWaitingForZonedDateTime = isWaitingForZonedDateTime;
    }
}
