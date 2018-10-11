package com.anuradha.fcmpushnotification.dto;

import java.io.Serializable;
import java.util.List;

public class TopicDTO implements Serializable {
    private long topic_id;
    private String topic;
    private List<UserDTO> userDTOS;

    public TopicDTO() {
    }

    public TopicDTO(long topic_id, String topic, List<UserDTO> userDTOS) {
        this.setTopic_id(topic_id);
        this.setTopic(topic);
        this.setUserDTOS(userDTOS);
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
                "topic_id=" + getTopic_id() +
                ", topic='" + getTopic() + '\'' +
                ", userDTOS=" + getUserDTOS() +
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

    public List<UserDTO> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }
}
