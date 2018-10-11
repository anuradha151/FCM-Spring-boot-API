package com.anuradha.fcmpushnotification.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String name;
    private String deviceToken;
    @ManyToMany(mappedBy = "users")
    private List<Topic> topics;

    public User() {
    }

    public User(String name, String deviceToken, List<Topic> topics) {
        this.setName(name);
        this.setDeviceToken(deviceToken);
        this.setTopics(topics);
    }

    @Override
    public String toString() {
        return "User{" +
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
