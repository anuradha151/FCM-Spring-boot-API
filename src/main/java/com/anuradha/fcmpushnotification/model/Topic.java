package com.anuradha.fcmpushnotification.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long topic_id;
    private String topic;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "topic_user",
            joinColumns = { @JoinColumn(name = "topic_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;

    public Topic() {
    }


}
