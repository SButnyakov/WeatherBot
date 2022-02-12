package com.app.bot;

import com.app.nonCommand.NonCommand;
import com.app.commands.operationCommands.WeatherNowCommand;
import com.app.commands.seviceCommands.HelpCommand;
import com.app.commands.seviceCommands.StartCommand;
import com.app.settings.Settings;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    public static final String API_TOKEN = "db082d6b19ebbda556c7e2e01d9b36b5";
    public static final OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);

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
            String userName = message.getFrom().toString();
            Long chatId = message.getChatId();
            if (message.hasText()) {
                String answer = NonCommand.nonCommandMessageExecute(message, chatId);
                try {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId.toString());
                    sendMessage.setText(answer);
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
