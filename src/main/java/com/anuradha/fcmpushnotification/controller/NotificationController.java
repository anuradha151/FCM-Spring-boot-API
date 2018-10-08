package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.impl.NotificationServiceImpl;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationServiceImpl notificationServiceImpl;

    @Autowired
    public NotificationController(NotificationServiceImpl notificationServiceImpl) {
        this.notificationServiceImpl = notificationServiceImpl;
    }

    @PostMapping("/to-topic")
    public ResponseEntity<?> sendToTopic(@RequestBody NotificationDTO notificationDTO) {
        try {
            return notificationServiceImpl.sendToTopic(notificationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Message sending failed..!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-topic")
    public ResponseEntity<?> createTopic(@RequestBody TopicDTO topicDTO) {
        try {
            return notificationServiceImpl.createTopic(topicDTO);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Topic creation failed...!", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(){
        return null;

    }



}
