package com.anuradha.fcmpushnotification.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificationDTO implements Serializable {

    private String title;
    private String topic;
    private String body;
    // The notification's icon color, expressed in #rrggbb format.
    private String color;
    private Date date;
    // Time-To-Live.How long the message will be kept in FCM storage if the target devices are offline.
    private long tTL;

    public NotificationDTO() {
    }

    public NotificationDTO(String title, String topic, String body, String color, Date date, long tTL) {
        this.setTitle(title);
        this.setTopic(topic);
        this.setBody(body);
        this.setColor(color);
        this.setDate(date);
        this.settTL(tTL);
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", body='" + body + '\'' +
                ", color='" + color + '\'' +
                ", date=" + date +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long gettTL() {
        return tTL;
    }

    public void settTL(long tTL) {
        this.tTL = tTL;
    }
}
