package com.app.helpers.api.weather;

import lombok.Data;

@Data
public class Pressure {
    private final int value;

    public Pressure(int value) {
        this.value = value;
    }
}
