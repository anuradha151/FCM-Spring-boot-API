package com.anuradha.fcmpushnotification.service;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) throws Exception;

    ResponseEntity<?> createTopic(TopicDTO topicDTO);

}
