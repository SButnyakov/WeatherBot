package com.app.helpers.api.weather;

import lombok.Data;

@Data
public class Snow {
    private final float value1h;
    private final float value3h;

    public Snow(float value1h, float value3h) {
        this.value1h = value1h;
        this.value3h = value3h;
    }

}
