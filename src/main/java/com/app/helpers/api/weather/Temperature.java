package com.app.helpers.api.weather;

public class Temperature {
    private final float value;
    private final float feelsLike;
    private final float min;
    private final float max;

    public Temperature(float value, float feelsLike, float min, float max) {
        this.value = value;
        this.feelsLike = feelsLike;
        this.min = min;
        this.max = max;
    }

    public float getFeelsLike() {
        return this.feelsLike;
    }

    public float getMin() {
        return this.min;
    }

    public float getMax() {
        return this.max;
    }

    public float getValue() {
        return this.value;
    }
}
