package com.app.settings;

public class Location {
    private final String country;
    private final String nameRu;
    private final String nameEn;
    private final float lat;
    private final float lon;

    public Location(String country, String nameRu, String nameEn, float lat, float lon) {
        this.country = country;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String toString() {
        return this.country +
                "\n" +
                this.nameRu +
                "\n" +
                this.nameEn +
                "\n" +
                this.lat +
                "\n" +
                this.lon +
                "\n";
    }
}
