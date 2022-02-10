package com.app.commands.operationCommands;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class OperationCommand extends BotCommand {
    private static final String API_TOKEN = "db082d6b19ebbda556c7e2e01d9b36b5";

    final OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);

    public OperationCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void sendAnswer(AbsSender sender, Long chatId, String commandName, String userName, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        try {
            sender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Тут должны быть методы для работы с OWM API
}
