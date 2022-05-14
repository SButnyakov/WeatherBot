package com.app.helpers.api.weather;

import lombok.Data;

@Data
public class Humidity {
    private final int value;

    public Humidity(int value) {
        this.value = value;
    }
}
