package com.anuradha.fcmpushnotification.service;


import com.anuradha.fcmpushnotification.dto.TopicDTO;
import org.springframework.http.ResponseEntity;

public interface TopicService {

    ResponseEntity<?> subscribe(TopicDTO topicDTO);

    ResponseEntity<?> unsubscribe(TopicDTO topicDTO);

    ResponseEntity<?> getAllTopics();

    ResponseEntity<?> findTopicByName(String topic);

}
