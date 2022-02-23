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
    private static final String FIRST_START_MESSAGE_RU = "Привет! Напиши город, в котором ты хочешь узнать погоду" +
            " или отправь мне свою геолокацию!";
    private static final String NORMAL_MESSAGE_RU = "Вот, что я умею:";

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        if (!Bot.settingsMap.containsKey(chat.getId()) || Bot.settingsMap.get(chat.getId()).getLocation() == null) {
            firstStart(absSender, userName, chat);
        } else {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, NORMAL_MESSAGE_RU);
        }
    }

    private void firstStart(AbsSender absSender, String userName, Chat chat) {
        Bot.settingsMap.put(chat.getId(), new Settings());
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, FIRST_START_MESSAGE_RU);
    }
}
