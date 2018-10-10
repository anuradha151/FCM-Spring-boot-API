package com.anuradha.fcmpushnotification.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String name;
    @ManyToMany(mappedBy = "users")
    private List<Topic> topics;

    public User() {
    }

    public User(String name, List<Topic> topics) {
        this.setName(name);
        this.setTopics(topics);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + getUid() +
                ", name='" + getName() + '\'' +
                ", topics=" + getTopics() +
                '}';
    }

    public int getUid() {
        return user_id;
    }

    public void setUid(int uid) {
        this.user_id = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
