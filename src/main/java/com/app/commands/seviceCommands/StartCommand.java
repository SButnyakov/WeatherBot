package com.app.commands.seviceCommands;

import com.app.bot.Bot;
import com.app.commands.Command;
import com.app.settings.Settings;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends Command {

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        Bot.settingsMap.put(chat.getId(), new Settings());
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Привет! Напиши город, в котором ты хочешь узнать погоду!");
    }
}
