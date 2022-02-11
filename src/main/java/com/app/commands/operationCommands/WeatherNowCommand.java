package com.app.commands.operationCommands;

import com.app.commands.Command;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class WeatherNowCommand extends Command {

    final String weather = openWeatherClient
            .currentWeather()
            .single()
            .byCityName("St. Petersburg")
            .language(Language.RUSSIAN)
            .unitSystem(UnitSystem.METRIC)
            .retrieve()
            .asJava()
            .toString();


    public WeatherNowCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Погода сейчас: " + weather);
    }
}
