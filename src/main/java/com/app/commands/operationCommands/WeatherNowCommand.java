package com.app.commands.operationCommands;

import com.app.bot.Bot;
import com.app.commands.Command;
import com.app.settings.Emojis;
import com.app.settings.Settings;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Rain;
import com.github.prominence.openweathermap.api.model.weather.Snow;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.model.weather.Wind;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.LocalTime;

public class WeatherNowCommand extends Command {

    public WeatherNowCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        Settings settings = Bot.settingsMap.get(chat.getId());
        final Weather weather = Bot.openWeatherClient
                .currentWeather()
                .single()
                .byCityName(settings.getCity())
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                makeWeatherNowString(weather));
    }

    private String makeWeatherNowString(Weather weather) {
        return getCityLine(weather) +
                "\n" +
                getTemperatureLineInCelsius(weather) +
                "\n" +
                getCloudinessLine(weather) +
                "\n" +
                getRainAndSnowLines(weather) +
                "\n" +
                getWindLine(weather) +
                "\n" +
                getHPaPressureLine(weather) +
                "\n" +
                getHumidityLine(weather);
    }

    private String getCityLine(Weather weather) {
        String city = weather.getLocation().getName();
        byte hour = (byte) LocalTime.now().getHour();
        city = (hour >= 7 && hour <= 12) ? Emojis.MORNING_CITY.hexCode  + " " + city : city;
        city = (hour >= 13 && hour <= 18) ? Emojis.DAY_CITY.hexCode  + " " + city : city;
        city = (hour >= 19) ? Emojis.EVENING_CITY.hexCode  + " " + city : city;
        city = (hour <= 6) ? Emojis.NIGHT_CITY.hexCode  + " " + city : city;
        return city + "\n";
    }

    private String getTemperatureLineInCelsius(Weather weather) {
        byte temp = (byte) Math.round(weather.getTemperature().getValue());
        String temperature = "";
        temperature = (temp >= 30) ? Emojis.THIRTY_AND_HIGHER.hexCode  + " " + temp : temperature;
        temperature = (temp >= 20 && temp < 30) ? Emojis.TWENTY_AND_TWENTY_NINE.hexCode  + " " + temp : temperature;
        temperature = (temp >= 10 && temp < 20) ? Emojis.TEN_AND_NINETEEN.hexCode  + " " + temp : temperature;
        temperature = (temp >= 1 && temp < 10) ? Emojis.ZERO_AND_NINE.hexCode  + " " + temp : temperature;
        temperature = (temp >= -15 && temp < 1) ? Emojis.SUB_FIFTEEN_AND_ONE.hexCode  + " " + temp : temperature;
        temperature = (temp >= -20 && temp < -15) ? Emojis.SUB_TWENTY_AND_SIXTEEN.hexCode  + " " + temp : temperature;
        temperature = (temp < -20) ? Emojis.SUB_TWENTY_ONE.hexCode  + " " + temp : temperature;
        return temperature + " °C\n";
    }

    private String getHumidityLine(Weather weather) {
        return Emojis.HUMIDITY + " " + weather.getHumidity().getValue() + "%";
    }

    private String getCloudinessLine(Weather weather) {
        byte cloud = weather.getClouds().getValue();
        String cloudiness = "";
        cloudiness = (cloud < 20) ? Emojis.CLEAR.hexCode  + " Ясно" : cloudiness;
        cloudiness = (cloud >= 20 && cloud < 40) ? Emojis.PARTLY_CLOUDY_1.hexCode  + " Малооблачно" : cloudiness;
        cloudiness = (cloud >= 40 && cloud < 60) ? Emojis.PARTLY_CLOUDY_2.hexCode  + " Переменная облачность" :
                cloudiness;
        cloudiness = (cloud >= 60 && cloud < 80) ? Emojis.PARTLY_CLOUDY_3.hexCode + " Облачно с прояснениями" :
                cloudiness;
        cloudiness = (cloud <= 100) ? Emojis.CLOUDY + " Облачно/Пасмурно" : cloudiness;
        return cloudiness + "\n";
    }

    private String getRainAndSnowLines(Weather weather) {
        Rain rainTemp = weather.getRain();
        Snow snowTemp = weather.getSnow();
        if (rainTemp != null && snowTemp != null) {
            // TODO: Доделать дождь + снег
        } else if (rainTemp != null) {
            double rain = rainTemp.getOneHourLevel();
            String rainLine = "";
            rainLine = (rain < 4.0) ? Emojis.SMALL_RAIN_1_2.hexCode  + " Небольшой дождь" : rainLine;
            rainLine = (rain >= 4.0 && rain < 15.0) ? Emojis.SMALL_RAIN_1_2.hexCode  + " Дождь" : rainLine;
            rainLine = (rain >= 15.0 && rain < 50.0) ? Emojis.RAIN_1_2.hexCode  + " Сильный дождь" : rainLine;
            rainLine = (rain >= 50.0) ? Emojis.RAIN_1_2.hexCode  + " Очень сильный дождь" : rainLine;
            return rainLine + "\n";
        } else if (snowTemp != null) {
            double snow = snowTemp.getOneHourLevel();
            String snowLine = "";
            snowLine = (snow < 4.0) ? Emojis.SNOW_1_2_3_4.hexCode  + " Небольшой снег" : snowLine;
            snowLine = (snow >= 4.0 && snow < 15.0) ? Emojis.SNOW_1_2_3_4.hexCode  + " Снег" : snowLine;
            snowLine = (snow >= 15.0 && snow < 50.0) ? Emojis.SNOW_1_2_3_4.hexCode  + " Сильный снег" : snowLine;
            snowLine = (snow >= 50.0) ? Emojis.SNOW_1_2_3_4.hexCode  + " Очень сильный снег" : snowLine;
            return snowLine + "\n";
        }
        return Emojis.NO_PRECIPITATION.hexCode  + " Без осадков!\n";
    }

    private String getWindLine(Weather weather) {
        Wind windTemp = weather.getWind();
        if (windTemp != null) {
            byte wind = (byte) Math.round(windTemp.getSpeed());
            String windLine = "";
            windLine = (wind <= 5) ? Emojis.WIND_1.hexCode  + " Слабый ветер" : windLine;
            windLine = (wind > 5 && wind <= 14) ? Emojis.WIND_2.hexCode  + " Умеренный ветер" : windLine;
            windLine = (wind > 14 & wind <= 24) ? Emojis.WIND_3.hexCode  + " Сильный ветер" : windLine;
            windLine = (wind > 24 && wind <= 32) ? Emojis.WIND_3.hexCode  + " Очень сильный ветер" : windLine;
            windLine = (wind > 32) ? Emojis.WIND_4.hexCode  + " Ураганный ветер" : windLine;
            return windLine + "\n";
        }
        return Emojis.WIND_1.hexCode  + " Штиль\n";
    }

    private String getHPaPressureLine(Weather weather) {
        return Emojis.PRESSURE.hexCode  + " " + Math.round(weather.getAtmosphericPressure().getValue()) + " hPa\n";
    }
}
