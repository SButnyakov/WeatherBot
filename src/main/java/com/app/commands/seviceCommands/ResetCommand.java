package com.app.commands.seviceCommands;

import com.app.bot.Bot;
import com.app.commands.Command;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class ResetCommand extends Command {
    private static final String FAILURE_MESSAGE_RU = "Да, я и так о вас ничего не знаю";
    private static final String SUCCESS_MESSAGE_RU = "Готово! Все настройки сброшены!";

    public ResetCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        String answer = "";
        if (Bot.settingsMap.containsKey(chat.getId()) && Bot.settingsMap.get(chat.getId()).getLocation() != null) {
            Bot.settingsMap.remove(chat.getId());
            answer = SUCCESS_MESSAGE_RU;
        } else {
            answer = FAILURE_MESSAGE_RU;
        }
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, answer);
    }
}
