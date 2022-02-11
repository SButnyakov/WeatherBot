package com.app.nonCommand;

import com.app.commands.Command;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class NonCommand {
    public static final String API_TOKEN = "db082d6b19ebbda556c7e2e01d9b36b5";
    public final OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);

    public String text;
    public Message message;

    public NonCommand(Message message){
        this.text = message.getText();
        this.message = message;
    }
    public Weather messageGetter () {
        try {
            final Weather weather = openWeatherClient
                    .currentWeather()
                    .single()
                    .byCityName(message.getText())
                    .language(Language.RUSSIAN)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava();
            return weather;
        } catch (NoDataFoundException e) {
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(message.getChatId().toString());
            errorMessage.setText("Такой город не найден. Попробуйте еще раз!");

            messageGetter();
        }
        return null;
    }
}
