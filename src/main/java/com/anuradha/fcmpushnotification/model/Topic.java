package com.anuradha.fcmpushnotification.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long topic_id;
    private String topic;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "topic_user",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    public Topic() {
    }

    public Topic(String topic, List<User> users) {
        this.setTopic(topic);
        this.setUsers(users);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + getTopic_id() +
                ", topic='" + getTopic() + '\'' +
                ", users=" + getUsers() +
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
