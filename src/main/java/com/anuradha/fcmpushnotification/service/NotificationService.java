package com.anuradha.fcmpushnotification.service;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) throws FirebaseMessagingException;

    ResponseEntity<?> createTopic(TopicDTO topicDTO) throws FirebaseMessagingException;

    ResponseEntity<?> unsubscribe(TopicDTO topicDTO) throws FirebaseMessagingException;

}
