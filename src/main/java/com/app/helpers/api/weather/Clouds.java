package com.app.helpers.api.weather;

import lombok.Data;

@Data
public class Clouds {
    private final int value;

    public Clouds(int value) {
        this.value = value;
    }
}
