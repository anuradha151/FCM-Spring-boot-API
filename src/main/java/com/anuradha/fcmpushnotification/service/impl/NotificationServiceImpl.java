package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.model.Notification;
import com.anuradha.fcmpushnotification.repository.NotificationRepository;
import com.anuradha.fcmpushnotification.service.NotificationService;
import com.anuradha.fcmpushnotification.service.TopicService;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {


    private final NotificationRepository notificationRepository;

    private final TopicService topicService;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, TopicService topicService) {
        this.topicService = topicService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) {



        Notification save = notificationRepository.save(dTOToEntity(notificationDTO));
        if (save == null) {
            return new ResponseEntity<>("Notification saving failed", HttpStatus.BAD_REQUEST);
        }
        String sendingType = notificationDTO.getSendingType();

        if (sendingType.equals("send-now")) {
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

            try {
                String response = FirebaseMessaging.getInstance().send(message);
                return new ResponseEntity<>("Message sent successfully\n" + response, HttpStatus.OK);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Notification sending failed. Try again", HttpStatus.EXPECTATION_FAILED);
            }
        }
        return new ResponseEntity<>("Notification sending in process,", HttpStatus.OK);


    }


    private Notification dTOToEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setBody(notificationDTO.getBody());
        notification.setColor(notificationDTO.getColor());
        notification.setDate(notificationDTO.getDate());
        notification.setStatus(notificationDTO.getStatus());
        notification.setTitle(notificationDTO.getTitle());
        notification.settTL(notificationDTO.gettTL());
        notification.setTopic(notificationDTO.getTopic());
        notification.setSendingType(notificationDTO.getSendingType());
        return notification;
    }


}
