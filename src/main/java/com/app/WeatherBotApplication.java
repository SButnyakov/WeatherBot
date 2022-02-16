package com.app;

import com.app.bot.Bot;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
;
public class WeatherBotApplication {
    private static final String BOT_NAME = "Weather";

    public static void main(String[] args) {
        //System.out.println(ZonedDateTime.now(ZoneId.ofOffset("UTC",
        //        ZoneOffset.ofHoursMinutes(19, 27))).toString());
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/opt/weatherbot/src/main/java/com/app/token.txt"));
            System.out.println("Zapusk bota!");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(BOT_NAME, reader.readLine()));
        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
        //final Forecast forecast = Bot.openWeatherClient
        //        .forecast5Day3HourStep()
        //        .byCityName("Minsk")
        //        .language(Language.ENGLISH)
        //        .unitSystem(UnitSystem.METRIC)
        //        .count(8)
        //        .retrieve()
        //        .asJava();
        //System.out.println(ZoneId.of("UTC").getId());
        //ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("UTC"));
        //System.out.println(zdt.getHour());

        //System.out.println(forecast.getWeatherForecasts());
    }
}
