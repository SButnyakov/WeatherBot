package com.app.nonCommand;

import com.app.settings.Settings;

import java.time.DateTimeException;

public abstract class SetZonedDateTimeCommand {
    private final static String SUCCESS_MESSAGE_RU = "Прекрасно! Ваш часовой пояс установлен!";
    private final static String FAILURE_MESSAGE_RU = "Что-то пошло не так. " +
            "Проверьте введёное время на соответствие формату";

    public static String setZonedDateTime(Settings settings, String currentTime) {
        try {
            String[] time = currentTime.split(":");
            int offsetHour = settings.getZonedDateTime().getHour() - Integer.parseInt(time[0]);
            int offsetMinute = settings.getZonedDateTime().getMinute() - Integer.parseInt(time[1]);
            settings.setZonedDateTime(offsetHour, offsetMinute);
            settings.setWaitingForZonedDateTime(false);
        } catch (NumberFormatException | DateTimeException e) {
            return FAILURE_MESSAGE_RU;
        }
        return SUCCESS_MESSAGE_RU;
    }
}
