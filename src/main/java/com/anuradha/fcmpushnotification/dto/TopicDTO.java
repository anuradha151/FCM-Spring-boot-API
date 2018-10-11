package com.anuradha.fcmpushnotification.dto;

import java.io.Serializable;
import java.util.List;

public class TopicDTO implements Serializable {
    private long topic_id;
    private String topic;
    private List<UserDTO> userDTOS;

    public TopicDTO() {
    }


}
