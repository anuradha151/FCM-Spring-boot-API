package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.service.impl.NotificationServiceImpl;
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


}
