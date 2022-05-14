package com.app.helpers.api.weather;

import lombok.Data;

@Data
public class Timezone {
    private final long value;

    public Timezone(long value) {
        this.value = value;
    }
}
