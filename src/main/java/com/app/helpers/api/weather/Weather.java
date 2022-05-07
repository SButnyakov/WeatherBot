package com.app.helpers.api.weather;

public class Weather {
    private final Clouds clouds;
    private final Humidity humidity;
    private final Pressure pressure;
    private final Rain rain;
    private final Snow snow;
    private final Temperature temperature;
    private final Visibility visibility;
    private final Wind wind;

    public Weather(Clouds clouds, Humidity humidity, Pressure pressure, Rain rain, Snow snow,
                   Temperature temperature, Visibility visibility, Wind wind) {
        this.clouds = clouds;
        this.humidity = humidity;
        this.pressure = pressure;
        this.rain = rain;
        this.snow = snow;
        this.temperature = temperature;
        this.visibility = visibility;
        this.wind = wind;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public Humidity getHumidity() {
        return this.humidity;
    }

    public Pressure getPressure() {
        return this.pressure;
    }

    public Rain getRain() {
        return this.rain;
    }

    public Snow getSnow() {
        return this.snow;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public Visibility getVisibility() {
        return this.visibility;
    }

    public Wind getWind() {
        return this.wind;
    }
}
