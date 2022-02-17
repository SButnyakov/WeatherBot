package com.app.commands.operationCommands;

import com.app.bot.Bot;
import com.app.commands.Command;
import com.app.commands.parser.WeatherMessageParser;
import com.app.settings.Settings;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class WeatherNowCommand extends Command {
    private static final String FAILURE_MESSAGE_RU = "Привязанный город не обнаружен.";

    public WeatherNowCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        if (!Bot.settingsMap.containsKey(chat.getId())) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, FAILURE_MESSAGE_RU);
        }
        Settings settings = Bot.settingsMap.get(chat.getId());
        if (settings.getCity() == null) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, FAILURE_MESSAGE_RU);
        }
        final Weather weather = Bot.openWeatherClient
                .currentWeather()
                .single()
                .byCityName(settings.getCity())
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                makeWeatherNowString(weather, settings));
    }

    private String makeWeatherNowString(Weather weather, Settings settings) {
        return WeatherMessageParser.getCityLine(weather) +
                WeatherMessageParser.getCurrentDateAndTime(settings.getZonedDateTime()) +
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
