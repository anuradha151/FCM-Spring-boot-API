package com.anuradha.fcmpushnotification.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String topic;
    private String body;
    // The notification's icon color, expressed in #rrggbb format.
    private String color;
    private Date date;
    // Time-To-Live.How long the message will be kept in FCM storage if the target devices are offline.
    private long tTL;
    private String status;
    // sending time - NOW/LATER/ANNUALLY
    private String condition;

    public Notification() {
    }

    public Notification(String title, String topic, String body, String color, Date date, long tTL, String status, String condition) {
        this.setTitle(title);
        this.setTopic(topic);
        this.setBody(body);
        this.setColor(color);
        this.setDate(date);
        this.settTL(tTL);
        this.setStatus(status);
        this.setCondition(condition);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", topic='" + getTopic() + '\'' +
                ", body='" + getBody() + '\'' +
                ", color='" + getColor() + '\'' +
                ", date=" + getDate() +
                ", tTL=" + gettTL() +
                ", status='" + getStatus() + '\'' +
                ", condition='" + getCondition() + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
