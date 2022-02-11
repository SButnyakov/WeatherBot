package com.app;

import com.app.bot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

public class WeatherBotApplication {
    private static final String BOT_NAME = "Weather";
    private static final String BOT_TOKEN = "5277154080:AAGGbt9DfMvmgnt3wZdc__Hfk1WkqyM6aao";

    public static void main(String[] args) {
        try {
            System.out.println("Zapusk bota!");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(BOT_NAME, BOT_TOKEN));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
