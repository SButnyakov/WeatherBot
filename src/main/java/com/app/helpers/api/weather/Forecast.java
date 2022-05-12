package com.app.helpers.api.weather;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

    private List<String> forecastTime = new ArrayList<String>();
    private List<Weather> forecastWeather = new ArrayList<Weather>();

    public void add(String time, Weather weather) {
        forecastTime.add(time);
        forecastWeather.add(weather);
    }

    public String getTime(int index) {
        return forecastTime.get(index);
    }

    public Weather getWeather(int index) {
        return forecastWeather.get(index);
    }
}
