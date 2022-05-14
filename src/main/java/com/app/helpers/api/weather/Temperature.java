package com.app.helpers.api.weather;

import lombok.Data;

@Data
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
}
