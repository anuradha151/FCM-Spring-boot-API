package com.anuradha.fcmpushnotification.dto;

import com.anuradha.fcmpushnotification.model.Topic;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private long user_id;
    private String name;
    private String deviceToken;
    private List<Topic> topics;

    public UserDTO() {
    }

    public UserDTO(long user_id, String name, String deviceToken, List<Topic> topics) {
        this.setUser_id(user_id);
        this.setName(name);
        this.setDeviceToken(deviceToken);
        this.setTopics(topics);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_id=" + getUser_id() +
                ", name='" + getName() + '\'' +
                ", deviceToken='" + getDeviceToken() + '\'' +
                ", topics=" + getTopics() +
                '}';
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
