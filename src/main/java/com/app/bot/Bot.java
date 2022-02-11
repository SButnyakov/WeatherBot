package com.app.bot;

import com.app.nonCommand.NonCommand;
import com.app.commands.operationCommands.WeatherNowCommand;
import com.app.commands.seviceCommands.HelpCommand;
import com.app.commands.seviceCommands.StartCommand;
import com.app.settings.Settings;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    public static final Map<Long, Settings> settingsMap = new HashMap<>();

    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        register(new StartCommand("start", "Начало работы"));
        register(new HelpCommand("help", "Помощь"));
        register(new WeatherNowCommand("now", "Погода сейчас"));
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String userName = message.getFrom().toString();

            // String answer = NonCommand.setAnswer(message);

        }

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                SendMessage sendMessage = new SendMessage();
                NonCommand nonCommand = new NonCommand(message);

                sendMessage.setChatId(message.getChatId().toString());
                sendMessage.setText(message.getText() + " <- Я повторил!"); // Тут должна быть инфа о погоде
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
