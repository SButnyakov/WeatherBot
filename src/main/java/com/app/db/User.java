package com.app.db;

import lombok.Data;

@Data
public class User {

    // @Id
    private Long chatId;
    private Float lat;
    private Float lon;
    private Boolean isNotificationNeeded;

}
