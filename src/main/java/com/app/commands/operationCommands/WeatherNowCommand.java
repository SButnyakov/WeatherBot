package com.app.commands.operationCommands;

import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class WeatherNowCommand extends OperationCommand {

    public WeatherNowCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    Weather weather = new Weather();
    weather.setLocation()

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Здесь будет выводиться погода сейчас");
    }
}
