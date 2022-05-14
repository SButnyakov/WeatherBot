package com.app.helpers.api.weather;

import lombok.Data;

@Data
public class Wind {
    private final float value;
    private final int deg;

    public Wind(float value, int deg) {
        this.value = value;
        this.deg = deg;
    }
}
