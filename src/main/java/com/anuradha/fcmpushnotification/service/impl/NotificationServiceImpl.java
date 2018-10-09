package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.NotificationService;
import com.google.firebase.messaging.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Scheduled(cron = "* * * * *")
    public static void testSchedule() {
        System.out.println("cron is working");
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

        String response = send(message, notificationDTO.getDate());

        if (response.equals("alive")) {
            return new ResponseEntity<>("Message sending in processes", HttpStatus.PROCESSING);
        }

        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

        // Create Response - return message id
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @Async
//    @Scheduled(cron = "1 * * * *")
    public String send(Message message, Date date) throws FirebaseMessagingException {

        testSchedule();
//        FirebaseMessaging.getInstance().send(message);
//        Date nowDate = new Date();
//        if (nowDate.equals(date)) {
//
//            System.out.println("message send method");
//
//            return FirebaseMessaging.getInstance().send(message);
//        }
        return "alive";


    }

    @Override
    public ResponseEntity<?> createTopic(TopicDTO topicDTO) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
                topicDTO.getDeviceTokens(),
                topicDTO.getTopic()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> unsubscribe(TopicDTO topicDTO) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(
                topicDTO.getDeviceTokens(),
                topicDTO.getTopic()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
