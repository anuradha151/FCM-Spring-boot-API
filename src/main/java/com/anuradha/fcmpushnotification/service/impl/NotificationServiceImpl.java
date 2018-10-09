package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.NotificationService;
import com.anuradha.fcmpushnotification.util.ScheduleTask;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {


    private final ScheduleTask scheduleTask;

    @Autowired
    public NotificationServiceImpl(ScheduleTask scheduleTask) {
        this.scheduleTask = scheduleTask;
    }

    @Override
    public ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) throws FirebaseMessagingException {


        // Create message payload
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        // Time to live - 4 weeks default and maximum
                        .setTtl(notificationDTO.gettTL()) //1 hour in milliseconds
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(
                                AndroidNotification
                                        .builder()
                                        .setTitle(notificationDTO.getTitle())
                                        .setBody(notificationDTO.getBody())
                                        .setColor(notificationDTO.getColor())
                                        .build()
                        )
                        .build())
                .setTopic(notificationDTO.getTopic())
                .build();

        // Send a message to the devices subscribed to the provided topic.
        String response = scheduleTask.send(message, notificationDTO.getDate());

        if (response.equals("alive")) {
            return new ResponseEntity<>("Message sending in processes", HttpStatus.PROCESSING);
        }

        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

        // Create Response - return message id
        return new ResponseEntity<>(response, HttpStatus.OK);


    }




}
