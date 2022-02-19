package com.app.helpers.api.weather;

public class Wind {
    private final float value;
    private final int deg;

    public Wind(float value, int deg) {
        this.value = value;
        this.deg = deg;
    }

    public float getValue() {
        return this.value;
    }

    public int getDeg() {
        return this.deg;
    }
}
