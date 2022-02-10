package com.app;

import com.app.bot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class WeatherBotApplication {
    private static final String BOT_NAME = "WeatherBot";
    private static final String BOT_TOKEN = "5171636256:AAGnR8-v0srYgX4tsvG1sYlt7IoGgP4Dz1E";
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(BOT_NAME, BOT_TOKEN));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
