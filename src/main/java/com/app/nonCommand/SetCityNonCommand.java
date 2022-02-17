package com.app.nonCommand;

import com.app.bot.Bot;
import com.app.helpers.api.API;
import com.app.settings.Location;
import com.app.settings.Settings;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;

import java.io.IOException;

public abstract class SetCityNonCommand {

    private final static String SUCCESS_MESSAGE_RU = "Город установлен!\n";
    private final static String FAILURE_MESSAGE_RU = "Такой город не найден. Попробуйте еще раз!";

    public static String setCity(Settings settings, String location) {
        try {
            settings.setLocation(API.makeLocationRequest(location));
            settings.setWaitingForCity(false);
        } catch (Exception e) {
            return FAILURE_MESSAGE_RU;
        }
        return SUCCESS_MESSAGE_RU;
    }
}
