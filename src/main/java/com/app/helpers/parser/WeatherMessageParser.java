package com.app.helpers.parser;

import com.app.helpers.api.weather.Weather;
import com.app.helpers.api.weather.Rain;
import com.app.helpers.api.weather.Snow;
import com.app.helpers.api.weather.Wind;
import com.app.settings.Emojis;
import com.app.settings.Location;

import java.time.LocalTime;

public abstract class WeatherMessageParser {

    public static String getTemperatureNowLineInCelsius(Weather weather) {
        int temp = Math.round(weather.getTemperature().getValue());
        String temperature = "";
        temperature = (temp >= 30) ? Emojis.THIRTY_AND_HIGHER + " " + temp : temperature;
        temperature = (temp >= 20 && temp < 30) ? Emojis.TWENTY_AND_TWENTY_NINE + " " + temp : temperature;
        temperature = (temp >= 10 && temp < 20) ? Emojis.TEN_AND_NINETEEN + " " + temp : temperature;
        temperature = (temp >= 1 && temp < 10) ? Emojis.ZERO_AND_NINE + " " + temp : temperature;
        temperature = (temp >= -15 && temp < 1) ? Emojis.SUB_FIFTEEN_AND_ONE + " " + temp : temperature;
        temperature = (temp >= -20 && temp < -15) ? Emojis.SUB_TWENTY_AND_SIXTEEN + " " + temp : temperature;
        temperature = (temp < -20) ? Emojis.SUB_TWENTY_ONE + " " + temp : temperature;
        return temperature + " °C\n";
    }

    // TODO: поменять время
    public static String getCityLine(String time, Weather weather, Location location) {
        String city = location.getNameRu();
        byte hour = Byte.parseByte(time.split(" ")[1].split(":")[0]);
        //byte hour = (byte) LocalTime.now().getHour();
        city = (hour >= 7 && hour <= 12) ? Emojis.MORNING_CITY + " " + city : city;
        city = (hour >= 13 && hour <= 18) ? Emojis.DAY_CITY + " " + city : city;
        city = (hour >= 19) ? Emojis.EVENING_CITY + " " + city : city;
        city = (hour <= 6) ? Emojis.NIGHT_CITY + " " + city : city;
        return city + "\n";
    }

    public static String getCloudinessLine(Weather weather) {
        int cloud = weather.getClouds().getValue();
        String cloudiness = "";
        cloudiness = (cloud < 20) ? Emojis.CLEAR + " Ясно" : cloudiness;
        cloudiness = (cloud >= 20 && cloud < 40) ? Emojis.PARTLY_CLOUDY_1 + " Малооблачно" : cloudiness;
        cloudiness = (cloud >= 40 && cloud < 60) ? Emojis.PARTLY_CLOUDY_2 + " Переменная облачность" :
                cloudiness;
        cloudiness = (cloud >= 60 && cloud < 80) ? Emojis.PARTLY_CLOUDY_3 + " Облачно с прояснениями" :
                cloudiness;
        cloudiness = (cloud >= 80 && cloud <= 100) ? Emojis.CLOUDY + " Облачно/Пасмурно" : cloudiness;
        return cloudiness + "\n";
    }

    public static String getRainAndSnowLines(Weather weather) {
        Rain rainTemp = weather.getRain();
        Snow snowTemp = weather.getSnow();
        if (rainTemp != null && snowTemp != null) {
            double sumRainSnow = rainTemp.getValue1h() + snowTemp.getValue1h();
            String rainAndSnowLine = "";
            rainAndSnowLine = (sumRainSnow <= 6.0) ?
                    Emojis.DRIPS.toString() + Emojis.LITTLE_SNOW.toString() + " Небольшой дождь со снегом" : rainAndSnowLine;
            rainAndSnowLine = (sumRainSnow >= 8 && sumRainSnow < 30) ?
                    Emojis.RAIN.toString() + Emojis.LITTLE_SNOW.toString() + " Дождь со снегом" : rainAndSnowLine;
            rainAndSnowLine = (sumRainSnow >= 30) ?
                    Emojis.RAIN.toString() + Emojis.SNOW_1_2_3_4.toString() + " Сильный дождь со снегом" : rainAndSnowLine;
            return rainAndSnowLine + "\n";
        }
        if (rainTemp != null) {
            double rain = rainTemp.getValue1h();
            String rainLine = "";
            rainLine = (rain < 4.0) ? Emojis.SMALL_RAIN_1_2 + " Небольшой дождь" : rainLine;
            rainLine = (rain >= 4.0 && rain < 15.0) ? Emojis.SMALL_RAIN_1_2 + " Дождь" : rainLine;
            rainLine = (rain >= 15.0 && rain < 50.0) ? Emojis.RAIN_1_2 + " Сильный дождь" : rainLine;
            rainLine = (rain >= 50.0) ? Emojis.RAIN_1_2 + " Очень сильный дождь" : rainLine;
            return rainLine + "\n";
        }
        if (snowTemp != null) {
            double snow = snowTemp.getValue1h();
            String snowLine = "";
            snowLine = (snow < 4.0) ? Emojis.SNOW_1_2_3_4 + " Небольшой снег" : snowLine;
            snowLine = (snow >= 4.0 && snow < 15.0) ? Emojis.SNOW_1_2_3_4 + " Снег" : snowLine;
            snowLine = (snow >= 15.0 && snow < 50.0) ? Emojis.SNOW_1_2_3_4 + " Сильный снег" : snowLine;
            snowLine = (snow >= 50.0) ? Emojis.SNOW_1_2_3_4 + " Очень сильный снег" : snowLine;
            return snowLine + "\n";
        }
        return Emojis.NO_PRECIPITATION + " Без осадков!\n";
    }

    public static String getWindLine(Weather weather) {
        Wind windTemp = weather.getWind();
        if (windTemp != null) {
            byte wind = (byte) Math.round(windTemp.getValue());
            String windLine = "";
            windLine = (wind <= 5) ? Emojis.WIND_1 + " Слабый ветер" : windLine;
            windLine = (wind > 5 && wind <= 14) ? Emojis.WIND_2 + " Умеренный ветер" : windLine;
            windLine = (wind > 14 & wind <= 24) ? Emojis.WIND_3 + " Сильный ветер" : windLine;
            windLine = (wind > 24 && wind <= 32) ? Emojis.WIND_3 + " Очень сильный ветер" : windLine;
            windLine = (wind > 32) ? Emojis.WIND_4 + " Ураганный ветер" : windLine;
            return windLine + "\n";
        }
        return Emojis.WIND_1 + " Штиль\n";
    }

    public static String getHPaPressureLine(Weather weather) {
        return Emojis.PRESSURE + " " + Math.round(weather.getPressure().getValue()) + " hPa\n";
    }

    public static String getHumidityLine(Weather weather) {
        return Emojis.HUMIDITY + " " + weather.getHumidity().getValue() + "%";
    }
}
