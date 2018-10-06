package com.anuradha.fcmpushnotification.dto;

public class NotificationDTO {

    private String title;
    private String topic;
    private String body;
    // The notification's icon color, expressed in #rrggbb format.
    private String color;
    // Time-To-Live.How long the message will be kept in FCM storage if the target devices are offline.
    private int tTL;

    public NotificationDTO() {
    }

    public NotificationDTO(String title, String topic, String body, String color, int tTL) {
        this.title = title;
        this.topic = topic;
        this.body = body;
        this.color = color;
        this.tTL = tTL;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", body='" + body + '\'' +
                ", color='" + color + '\'' +
                ", tTL=" + tTL +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int gettTL() {
        return tTL;
    }

    public void settTL(int tTL) {
        this.tTL = tTL;
    }
}
