package com.anuradha.fcmpushnotification.service;


import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;

public interface TopicService {

    ResponseEntity<?> subscribe(TopicDTO topicDTO) throws FirebaseMessagingException;

    ResponseEntity<?> unsubscribe(TopicDTO topicDTO) throws FirebaseMessagingException;

    ResponseEntity<?> getAllTopics();

    ResponseEntity<?> findTopicByName(String topic);

}
