package com.anuradha.fcmpushnotification.dto;

import com.anuradha.fcmpushnotification.model.Topic;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private int user_id;
    private String name;
    private String deviceToken;
    private List<Topic> topics;


}
