package com.app.helpers.api;

import com.app.helpers.api.weather.*;
import com.app.settings.Location;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class API {
    public static final String API_TOKEN = "db082d6b19ebbda556c7e2e01d9b36b5";
    private static final String urlGeoDirect = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private static final String urlCurrentWeather = "http://api.openweathermap.org/data/2.5/weather?lat=";

    /*

    Пока не добавим клавиатуру будем брать первый город,
    потом предлагать полученный список из 5

    public static JSONObject makeCitiesRequest() {
        String getCities = Request.Get(urlGeo)
                .execute().returnContent().toString();
        JSONArray jsonCities = new JSONArray(getCities);
    }

     */

    public static Weather makeCurrentWeatherRequest(float lat, float lon) throws IOException {
        JSONObject json = readJsonFromUrl(makeUrlForCurrentWeatherRequest(lat, lon));
        return getCurrentWeatherFromJSON(json);
    }

    public static Location makeLocationRequest(String location) throws Exception {
        final String getResult = Request
                .Get(makeURLForDirectLocationRequest(location, "1"))
                .execute()
                .returnContent()
                .toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        Location newLocation = getLocationFromJSON(new JSONObject(getResult));
        newLocation.setTimezone(makeTimezoneRequest(newLocation.getLat(), newLocation.getLon()));
        return newLocation;
    }

    private static Timezone makeTimezoneRequest(float lat, float lon) throws IOException {
        JSONObject json = readJsonFromUrl(makeUrlForCurrentWeatherRequest(lat, lon));
        return new Timezone(json.getLong("timezone"));
    }

    private static Weather getCurrentWeatherFromJSON(JSONObject json) {
        Rain rain;
        Snow snow;
        try {
            rain = new Rain(json.getJSONObject("rain").getFloat("1h"),
                    json.getJSONObject("rain").getFloat("3h"));
        } catch (Exception e) {
            rain = null;
        }
        try {
            snow = new Snow(json.getJSONObject("snow").getFloat("1h"),
                    json.getJSONObject("rain").getFloat("3h"));
        } catch (Exception e) {
            snow = null;
        }
        return new Weather(new Clouds(json.getJSONObject("clouds").getInt("all")),
                new Humidity(json.getJSONObject("main").getInt("humidity")),
                new Pressure(json.getJSONObject("main").getInt("pressure")),
                rain, snow,
                new Temperature(json.getJSONObject("main").getFloat("temp"),
                        json.getJSONObject("main").getFloat("feels_like"),
                        json.getJSONObject("main").getFloat("temp_min"),
                        json.getJSONObject("main").getFloat("temp_max")),
                new Visibility(json.getInt("visibility")),
                new Wind(json.getJSONObject("wind").getFloat("speed"),
                        json.getJSONObject("wind").getInt("deg")),
                new Description(json.getJSONArray("weather").getJSONObject(0).getString("description")));
    }

    private static Location getLocationFromJSON(JSONObject json) {
        return new Location(json.getString("country"),
                json.getJSONObject("local_names").getString("ru"),
                json.getJSONObject("local_names").getString("en"),
                json.getFloat("lat"),
                json.getFloat("lon"));
    }

    private static String makeURLForDirectLocationRequest(String location, String limit) {
        return urlGeoDirect + location + "&limit=" + limit + "&appid=" + API_TOKEN;
    }

    public static String makeUrlForCurrentWeatherRequest(float lat, float lon) {
        return urlCurrentWeather + lat + "&lon=" + lon + "&units=metric&appid=" + API_TOKEN + "&lang=ru";
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            return new JSONObject(readAll(rd));
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
