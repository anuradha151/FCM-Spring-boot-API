package com.anuradha.fcmpushnotification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long topic_id;
    private String topic;
    private List<String> deviceTokens;

    public Topic() {
    }

    public Topic(long topic_id, String topic, List<String> deviceTokens) {
        this.setTopic_id(topic_id);
        this.setTopic(topic);
        this.setDeviceTokens(deviceTokens);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + getTopic_id() +
                ", topic='" + getTopic() + '\'' +
                ", deviceTokens=" + getDeviceTokens() +
                '}';
    }

    public long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getDeviceTokens() {
        return deviceTokens;
    }

    public void setDeviceTokens(List<String> deviceTokens) {
        this.deviceTokens = deviceTokens;
    }
}
