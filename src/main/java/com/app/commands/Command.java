package com.app.commands;

import com.app.settings.Settings;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Command extends BotCommand {

    public Command(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public Settings makeBackup(Settings previousSettings) {
        return new Settings(previousSettings);
    }

    public void sendAnswer(AbsSender sender, Long chatId, String commandName, String userName, String text) {
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
}
