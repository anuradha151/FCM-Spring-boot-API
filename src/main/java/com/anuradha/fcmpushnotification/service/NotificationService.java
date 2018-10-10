package com.anuradha.fcmpushnotification.service;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) throws FirebaseMessagingException;

    ResponseEntity<?> saveNotification(NotificationDTO notificationDTO);


}
