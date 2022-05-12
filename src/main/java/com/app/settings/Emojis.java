package com.app.settings;

public enum Emojis {
    MORNING_CITY("\ud83c\udf07"),
    DAY_CITY("\uD83C\uDFD9"),
    EVENING_CITY("\ud83c\udf06"),
    NIGHT_CITY("\ud83c\udf03"),
    THIRTY_AND_HIGHER("\ud83d\udd25"),
    TWENTY_AND_TWENTY_NINE("\ud83e\udd75"),
    TEN_AND_NINETEEN("\ud83d\ude0e"),
    ZERO_AND_NINE("\ud83e\udde5"),
    SUB_FIFTEEN_AND_ONE("\u2744\ufe0f"),
    SUB_TWENTY_AND_SIXTEEN("\ud83e\udde3"),
    SUB_TWENTY_ONE("\ud83e\udd76"),
    HUMIDITY("\ud83d\udca7"),
    SNOW_1_2_3_4("\ud83c\udf28"),
    CLEAR("\u2600\ufe0f"),
    PARTLY_CLOUDY_1("\ud83c\udf24"),
    PARTLY_CLOUDY_2("\u26c5\ufe0f"),
    PARTLY_CLOUDY_3("\ud83c\udf25"),
    CLOUDY("\u2601\ufe0f"),
    SMALL_RAIN_1_2("\ud83c\udf26"),
    RAIN_1_2("\ud83c\udf26"),
    RAIN("\ud83c\udf27"),
    DRIPS("\ud83d\udca6"),
    LITTLE_SNOW("\u2744\ufe0f"),
    NO_PRECIPITATION("\ud83c\udf08"),
    WIND_1("\ud83c\udf43"),
    WIND_2("\ud83d\udca8"),
    WIND_3("\ud83c\udf2c"),
    WIND_4("\ud83c\udf2a"),
    PRESSURE("\ud83c\udf21");

    @Override
    public String toString() {
        return hexCode;
    }

    private final String hexCode;

    private Emojis(String hexCode) {
        this.hexCode = hexCode;
    }
}
