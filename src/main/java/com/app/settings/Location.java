package com.app.settings;

import com.app.helpers.api.weather.Timezone;

public class Location {
    private final String country;
    private final String nameRu;
    private final String nameEn;
    private final float lat;
    private final float lon;
    private Timezone timezone;

    public Location(String country, String nameRu, String nameEn, float lat, float lon) {
        this.country = country;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.lat = lat;
        this.lon = lon;
        this.timezone = null;
    }

    public String getCountry() {
        return this.country;
    }

    public String getNameRu() {
        return this.nameRu;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public float getLat() {
        return this.lat;
    }

    public float getLon() {
        return this.lon;
    }

    public Timezone getTimezone() {
        return this.timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }
}
