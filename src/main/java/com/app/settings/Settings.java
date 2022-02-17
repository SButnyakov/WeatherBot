package com.app.settings;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Settings {
    private Units.Temperature temperature;
    private Units.Pressure pressure;
    private Location location;
    private boolean isWaitingForCity;
    private boolean language;
    private ZonedDateTime zonedDateTime;

    public Settings() {
        this.temperature = Units.Temperature.CELSIUS;
        this.pressure = Units.Pressure.MILLIMETERS;
        this.location = null;
        this.isWaitingForCity = true;
        this.language = true;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    // Конструктор для бэкапов
    public Settings(Settings settings) {
        this.temperature = settings.temperature;
        this.pressure = settings.pressure;
        this.location = settings.location;
        this.isWaitingForCity = settings.isWaitingForCity;
        this.language = settings.language;
        this.zonedDateTime = settings.zonedDateTime;
    }

    public Units.Temperature getTemperature() {
        return this.temperature;
    }

    public Units.Pressure getPressure() {
        return this.pressure;
    }

    public Location getLocation() {
        return this.location;
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

    public void setTemperature(Units.Temperature temperature) {
        this.temperature = temperature;
    }

    public void setPressure(Units.Pressure pressure) {
        this.pressure = pressure;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setWaitingForCity(boolean isWaitingForCity) {
        this.isWaitingForCity = isWaitingForCity;
    }

    public void setLanguage(boolean language) {
        this.language = language;
    }

    public void setZonedDateTime(int hoursOffset) {
        this.zonedDateTime = ZonedDateTime.now(ZoneId.ofOffset("UTC",
                ZoneOffset.ofHours(hoursOffset)));
    }
}
