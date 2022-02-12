package com.app.settings;

public class Settings {
    private boolean firstTimeHere;
    private Units.Temperature temperature;
    private Units.Pressure pressure;
    private String city;
    private boolean isWaitingForCity;
    private boolean language;

    public Settings() {
        this.firstTimeHere = true;
        this.temperature = Units.Temperature.CELSIUS;
        this.pressure = Units.Pressure.MILLIMETERS;
        this.city = null;
        this.isWaitingForCity = true;
        this.language = true;
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

    public boolean isFirstTimeHere() {
        return this.firstTimeHere;
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
}
