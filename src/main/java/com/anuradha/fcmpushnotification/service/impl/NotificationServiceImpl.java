package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.model.ResponseModel;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {

    public ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) throws Exception {

        String response;

        // See documentation on defining a message payload.

        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        // Time to live - 4 weeks default and maximum
                        .setTtl(notificationDTO.gettTL()) //1 hour in milliseconds
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(AndroidNotification.builder()
                                .setTitle(notificationDTO.getTitle())
                                .setBody(notificationDTO.getBody())
                                .setColor(notificationDTO.getColor())
                                .build())
                        .build())
                .setTopic(notificationDTO.getTopic())
                .build();
        // Send a message to the devices subscribed to the provided topic.


        System.out.println(FirebaseMessaging.getInstance());

        response = FirebaseMessaging.getInstance().send(message);


        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

        // Create Response
        ResponseModel res = new ResponseModel(HttpStatus.OK.value(), "Notification sent successfully", true);
        return new ResponseEntity<>(res, HttpStatus.OK);


    }

}
