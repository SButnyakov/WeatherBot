package com.app.nonCommand;

import com.app.bot.Bot;
import com.app.settings.Settings;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;

public abstract class SetCityNonCommand {

    private final static String SUCCESS_MESSAGE = "Город установлен";
    private final static String FAILURE_MESSAGE = "Такой город не найден. Попробуйте еще раз!";

    public static String setCity(Settings settings, String city) {
        try {
            Bot.openWeatherClient.currentWeather().single().byCityName(city).retrieve().asJava();
            settings.setCity(city);
            settings.setWaitingForCity(false);
        } catch (NoDataFoundException e) {
            return FAILURE_MESSAGE;
        }
        return SUCCESS_MESSAGE;
    }
}
