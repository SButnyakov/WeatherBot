package com.app;

import com.app.bot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
;
public class WeatherBotApplication {
    private static final String BOT_NAME = "Weather";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/opt/weatherbot/src/main/java/com/app/token.txt"));
            System.out.println("Zapusk bota!");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(BOT_NAME, reader.readLine()));
        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
