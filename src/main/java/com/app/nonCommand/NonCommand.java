package com.app.nonCommand;

import com.app.bot.Bot;
import com.app.settings.Settings;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class NonCommand {
    private final static String DO_NOT_UNDERSTAND_MESSAGE_RU = "Простите, я вас не понимаю";

    public static String nonCommandMessageExecute(Message message, Long chatId) {
        Settings settings = Bot.settingsMap.get(chatId);
        if (settings.isWaitingForCity()) {
            String city = message.getText();
            return SetCityNonCommand.setCity(settings, city);
        }
        return DO_NOT_UNDERSTAND_MESSAGE_RU;
    }

}
