package com.app.settings;

import com.app.helpers.api.weather.Pressure;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Settings {
    private Location location;
    private boolean isWaitingForCity;
    private ZonedDateTime zonedDateTime;

    public Settings() {
        this.location = null;
        this.isWaitingForCity = true;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public Location getLocation() {
        return this.location;
    }

    public boolean isWaitingForCity() {
        return this.isWaitingForCity;
    }

    public ZonedDateTime getZonedDateTime() {
        return this.zonedDateTime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setWaitingForCity(boolean isWaitingForCity) {
        this.isWaitingForCity = isWaitingForCity;
    }

    public void setZonedDateTime(int hoursOffset) {
        this.zonedDateTime = ZonedDateTime.now(ZoneId.ofOffset("UTC",
                ZoneOffset.ofHours(hoursOffset)));
    }
}
