package com.anuradha.fcmpushnotification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String deviceToken;

    public User() {
    }

    public User(int uid, String name, String deviceToken) {
        this.setUid(uid);
        this.setName(name);
        this.setDeviceToken(deviceToken);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + getUid() +
                ", name='" + getName() + '\'' +
                ", deviceToken='" + getDeviceToken() + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
}
