package com.app.commands.operationCommands;

import com.app.bot.Bot;
import com.app.commands.Command;
import com.app.helpers.api.API;
import com.app.helpers.api.weather.Weather;
import com.app.helpers.parser.WeatherMessageParser;
import com.app.settings.Location;
import com.app.settings.Settings;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class WeatherNowCommand extends Command {
    private static final String FAILURE_MESSAGE_RU = "Привязанный город не обнаружен.";
    private static final String ERROR_MESSAGE_RU = "Что-то на нашей стороне пошло не так, " +
            "мы уже об этом узнали и будем пытаться исправить";

    public WeatherNowCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        if (!Bot.settingsMap.containsKey(chat.getId())) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, FAILURE_MESSAGE_RU);
            return;
        }
        Settings settings = Bot.settingsMap.get(chat.getId());
        if (settings.getLocation() == null) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, FAILURE_MESSAGE_RU);
            return;
        }
        final Location location = Bot.settingsMap.get(chat.getId()).getLocation();
        try {
            Weather weather = API.makeCurrentWeatherRequest(location.getLat(), location.getLon());
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                    makeWeatherNowString(weather, location));
        } catch (Exception e) {
            e.printStackTrace();
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, ERROR_MESSAGE_RU);
        }
    }

    private String makeWeatherNowString(Weather weather, Location location) {
        return WeatherMessageParser.getCityLine(weather, location) +
                "\n" +
                WeatherMessageParser.getTemperatureNowLineInCelsius(weather) +
                "\n" +
                WeatherMessageParser.getCloudinessLine(weather) +
                "\n" +
                WeatherMessageParser.getRainAndSnowLines(weather) +
                "\n" +
                WeatherMessageParser.getWindLine(weather) +
                "\n" +
                WeatherMessageParser.getHPaPressureLine(weather) +
                "\n" +
                WeatherMessageParser.getHumidityLine(weather);
    }
}
