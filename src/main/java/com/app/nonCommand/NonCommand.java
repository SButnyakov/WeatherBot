package com.app.nonCommand;

import com.app.bot.Bot;
import com.app.settings.Settings;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class NonCommand {

    public static String nonCommandMessageExecute(Message message, String chatId) {
        Settings settings = Bot.settingsMap.get(chatId);
        try {
            if (settings.isWaitingForCity()) {
                String city = message.getText();
                SetCityNonCommand.setCity(settings, city);
            }

        } catch (NoDataFoundException e) {
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(message.getChatId().toString());
            errorMessage.setText("Такой город не найден. Попробуйте еще раз!");
            return errorMessage.toString();
        }
        return null;
    }

}
