package com.app.commands.seviceCommands;

import com.app.bot.Bot;
import com.app.commands.Command;
import com.app.settings.Settings;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;

public class StartCommand extends Command {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        if (Bot.settingsMap.get(chat.getId()).getCity() == null) {
            firstStart(absSender, userName, chat);
        } else {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, "Вот, что я умею:");
        }
    }

    private void firstStart(AbsSender absSender, String userName, Chat chat) {
        Bot.settingsMap.put(chat.getId(), new Settings());
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Привет! Напиши город, в котором ты хочешь узнать погоду или отправь свою геолокацию!");
        // Добавляем клавиатуру
        /*
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow geolocationRow = new KeyboardRow();
        keyboard.clear();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        geolocationRow.add("\ud83c\udff4\u200d\u2620\ufe0f" + " Геолокация");
        keyboard.add(geolocationRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
         */
    }
}
