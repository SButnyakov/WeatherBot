package com.app.helpers.api;

import com.app.settings.Location;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class API {
    public static final String API_TOKEN = "db082d6b19ebbda556c7e2e01d9b36b5";
    private static final String urlGeoDirect = "http://api.openweathermap.org/geo/1.0/direct?q=";

    /*

    Пока не добавим клавиатуру будем брать первый город,
    потом предлагать полученный список из 5

    public static JSONObject makeCitiesRequest() {
        String getCities = Request.Get(urlGeo)
                .execute().returnContent().toString();
        JSONArray jsonCities = new JSONArray(getCities);
    }

     */

    public static Location makeLocationRequest(String location) throws Exception {
        final String getResult = Request
                .Get(makeURLForDirectRequest(location, "1"))
                .execute()
                .returnContent()
                .toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        return getLocationFromJSON(new JSONObject(getResult));
    }

    private static Location getLocationFromJSON(JSONObject json) {
        return new Location(json.getString("country"),
                json.getJSONObject("local_names").getString("ru"),
                json.getJSONObject("local_names").getString("en"),
                json.getFloat("lat"),
                json.getFloat("lon"));
    }

    public static String makeURLForDirectRequest(String location, String limit) {
        return urlGeoDirect + location + "&limit=" + limit + "&appid=" + API_TOKEN;
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
