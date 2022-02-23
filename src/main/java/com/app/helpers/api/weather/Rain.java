package com.app.helpers.api.weather;

public class Rain {
    private final float value1h;
    private final float value3h;

    public Rain(float value1h, float value3h) {
        this.value1h = value1h;
        this.value3h = value3h;
    }

    public float getValue1h() {
        return this.value1h;
    }

    public float getValue3h() {
        return this.value3h;
    }
}
