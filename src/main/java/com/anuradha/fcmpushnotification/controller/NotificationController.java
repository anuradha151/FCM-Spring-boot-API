package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        return notificationServiceImpl.sendToTopic(notificationDTO);
    }

    @GetMapping("/initializeFCM")
    public ResponseEntity<?> initializeFCM() {
        return notificationServiceImpl.initializeFCM();
    }


}
