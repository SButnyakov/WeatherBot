package com.app.settings;

import com.app.helpers.api.weather.Timezone;

import lombok.Data;

@Data
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
}
