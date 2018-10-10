package com.anuradha.fcmpushnotification.model;

import java.util.Date;

public class Notification {
    private String title;
    private String topic;
    private String body;
    // The notification's icon color, expressed in #rrggbb format.
    private String color;
    private Date date;
    // Time-To-Live.How long the message will be kept in FCM storage if the target devices are offline.
    private long tTL;
}
