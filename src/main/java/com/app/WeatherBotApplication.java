package com.app;

import com.app.bot.Bot;
import com.app.helpers.api.API;
import com.app.settings.Location;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
;
public class WeatherBotApplication {
    private static final String BOT_NAME = "Weather";

    public static void main(String[] args) throws Exception {

        try {
            //BufferedReader reader = new BufferedReader(new FileReader("/opt/weatherbot/src/main/java/com/app/token.txt"));
            System.out.println("Zapusk bota!");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(BOT_NAME, "5325267103:AAGqNrN5gDdQDeEsZeDnPCcZHR0gImYowT8"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /*
        System.out.println(Request
                .Get(makeURLForDirectLocationRequest("Токио", "1"))
                .execute()
                .returnContent()
                .toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", ""));

        JSONObject forecast = readJsonFromUrl(makeUrlForCurrentWeatherRequest((float) 35.6828387, (float) 139.7594549));
        System.out.println(forecast.getJSONArray("list"));
        System.out.println(forecast.getJSONArray("list").getJSONObject(0).getString("dt_txt"));
        System.out.println(forecast.getJSONArray("list").getJSONObject(0).getJSONObject("main"));
    }

    public static final String API_TOKEN = "db082d6b19ebbda556c7e2e01d9b36b5";
    private static final String urlGeoDirect = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private static final String urlCurrentWeather = "http://api.openweathermap.org/data/2.5/forecast?lat=";

    private static String makeURLForDirectLocationRequest(String location, String limit) {
        return urlGeoDirect + location + "&limit=" + limit + "&appid=" + API_TOKEN;
    }

    public static String makeUrlForCurrentWeatherRequest(float lat, float lon) {
        return urlCurrentWeather + lat + "&lon=" + lon + "&units=metric&appid=" + API_TOKEN + "&lang=ru";
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }

         */
    }
}
