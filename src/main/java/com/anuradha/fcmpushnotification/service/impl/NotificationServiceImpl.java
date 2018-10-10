package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.model.Notification;
import com.anuradha.fcmpushnotification.repository.NotificationRepository;
import com.anuradha.fcmpushnotification.service.NotificationService;
import com.anuradha.fcmpushnotification.util.ScheduleTask;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {


    private final ScheduleTask scheduleTask;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(ScheduleTask scheduleTask, NotificationRepository notificationRepository) {
        this.scheduleTask = scheduleTask;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) throws FirebaseMessagingException {
        Notification save = notificationRepository.save(dTOToEntity(notificationDTO));
        if (save == null) {
            return new ResponseEntity<>("Notification saving failed", HttpStatus.BAD_REQUEST);
        }
        if (notificationDTO.getCondition().equals("send-now")){

        }


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

    @Override
    public ResponseEntity<?> saveNotification(NotificationDTO notificationDTO) {

        return new ResponseEntity<>("Notification saved successfully", HttpStatus.OK);
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
        notification.setCondition(notificationDTO.getCondition());
        return notification;
    }


}
