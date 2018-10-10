package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/to-topic")
    public ResponseEntity<?> sendToTopic(@RequestBody NotificationDTO notificationDTO) {

        System.out.println("date and time : " + notificationDTO.getDate());

        try {
            return notificationService.sendToTopic(notificationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Message sending failed..!", HttpStatus.BAD_REQUEST);
        }
    }




}
