package com.app.commands.parser;

import com.app.settings.Emojis;
import com.github.prominence.openweathermap.api.model.weather.Rain;
import com.github.prominence.openweathermap.api.model.weather.Snow;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.model.weather.Wind;

import java.time.LocalTime;
import java.time.ZonedDateTime;

public abstract class WeatherMessageParser {

    public static String getTemperatureNowLineInCelsius(Weather weather) {
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

    public static String getCityLine(Weather weather) {
        String city = weather.getLocation().getName();
        byte hour = (byte) LocalTime.now().getHour();
        city = (hour >= 7 && hour <= 12) ? Emojis.MORNING_CITY.hexCode  + " " + city : city;
        city = (hour >= 13 && hour <= 18) ? Emojis.DAY_CITY.hexCode  + " " + city : city;
        city = (hour >= 19) ? Emojis.EVENING_CITY.hexCode  + " " + city : city;
        city = (hour <= 6) ? Emojis.NIGHT_CITY.hexCode  + " " + city : city;
        return city + "\n";
    }

    public static String getCloudinessLine(Weather weather) {
        byte cloud = weather.getClouds().getValue();
        String cloudiness = "";
        cloudiness = (cloud < 20) ? Emojis.CLEAR.hexCode  + " Ясно" : cloudiness;
        cloudiness = (cloud >= 20 && cloud < 40) ? Emojis.PARTLY_CLOUDY_1.hexCode  + " Малооблачно" : cloudiness;
        cloudiness = (cloud >= 40 && cloud < 60) ? Emojis.PARTLY_CLOUDY_2.hexCode  + " Переменная облачность" :
                cloudiness;
        cloudiness = (cloud >= 60 && cloud < 80) ? Emojis.PARTLY_CLOUDY_3.hexCode + " Облачно с прояснениями" :
                cloudiness;
        cloudiness = (cloud <= 100) ? Emojis.CLOUDY.hexCode + " Облачно/Пасмурно" : cloudiness;
        return cloudiness + "\n";
    }

    public static String getRainAndSnowLines(Weather weather) {
        Rain rainTemp = weather.getRain();
        Snow snowTemp = weather.getSnow();
        if (rainTemp != null && snowTemp != null) {
            double sumRainSnow = rainTemp.getOneHourLevel() + snowTemp.getOneHourLevel();
            String rainAndSnowLine = "";
            rainAndSnowLine = (sumRainSnow <= 6.0) ?
                    Emojis.DRIPS.hexCode + Emojis.LITTLE_SNOW.hexCode + " Небольшой дождь со снегом" : rainAndSnowLine;
            rainAndSnowLine = (sumRainSnow >= 8 && sumRainSnow < 30) ?
                    Emojis.RAIN.hexCode + Emojis.LITTLE_SNOW.hexCode + " Дождь со снегом" : rainAndSnowLine;
            rainAndSnowLine = (sumRainSnow >= 30) ?
                    Emojis.RAIN.hexCode + Emojis.SNOW_1_2_3_4.hexCode + " Сильный дождь со снегом" : rainAndSnowLine;
            return rainAndSnowLine + "\n";
        }
        if (rainTemp != null) {
            double rain = rainTemp.getOneHourLevel();
            String rainLine = "";
            rainLine = (rain < 4.0) ? Emojis.SMALL_RAIN_1_2.hexCode  + " Небольшой дождь" : rainLine;
            rainLine = (rain >= 4.0 && rain < 15.0) ? Emojis.SMALL_RAIN_1_2.hexCode  + " Дождь" : rainLine;
            rainLine = (rain >= 15.0 && rain < 50.0) ? Emojis.RAIN_1_2.hexCode  + " Сильный дождь" : rainLine;
            rainLine = (rain >= 50.0) ? Emojis.RAIN_1_2.hexCode  + " Очень сильный дождь" : rainLine;
            return rainLine + "\n";
        }
        if (snowTemp != null) {
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

    public static String getWindLine(Weather weather) {
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

    public static String getHPaPressureLine(Weather weather) {
        return Emojis.PRESSURE.hexCode  + " " + Math.round(weather.getAtmosphericPressure().getValue()) + " hPa\n";
    }

    public static String getHumidityLine(Weather weather) {
        return Emojis.HUMIDITY.hexCode + " " + weather.getHumidity().getValue() + "%";
    }

    public static String getCurrentDateAndTime(ZonedDateTime zonedDateTime) {
        return " (" + zonedDateTime.getDayOfMonth() +
                "." +
                zonedDateTime.getMonthValue() +
                " " +
                zonedDateTime.getHour() +
                ":" +
                zonedDateTime.getMinute() +
                ")";
    }
}
