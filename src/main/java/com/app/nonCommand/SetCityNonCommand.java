package com.app.nonCommand;

import com.app.settings.Settings;

public abstract class SetCityNonCommand {

    public static String setCity(Settings settings, String city) {
        settings.setCity(city);
        return null;
    }
}
