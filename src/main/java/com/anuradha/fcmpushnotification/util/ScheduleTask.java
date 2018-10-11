package com.anuradha.fcmpushnotification.util;

import com.anuradha.fcmpushnotification.model.Notification;
import com.anuradha.fcmpushnotification.repository.NotificationRepository;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduleTask {

    private final NotificationRepository notificationRepository;

    @Autowired
    public ScheduleTask(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    private void getAllPending() {

        System.out.println("getAllPending");

        List<Notification> allPending = notificationRepository.getAllPending();
        if (!allPending.isEmpty()) {
            Date nowDate = new Date();
            for (Notification notification : allPending) {
                if (nowDate.equals(notification.getDate())) {
                    Message message = createMessage(notification);

                    System.out.println("message send method");

                    try {
                        FirebaseMessaging.getInstance().send(message);
                    } catch (FirebaseMessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private Message createMessage(Notification notification) {
        return Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        // Time to live - 4 weeks default and maximum
                        .setTtl(notification.gettTL()) //1 hour in milliseconds
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(
                                AndroidNotification
                                        .builder()
                                        .setTitle(notification.getTitle())
                                        .setBody(notification.getBody())
                                        .setColor(notification.getColor())
                                        .build()
                        )
                        .build())
                .setTopic(notification.getTopic())
                .build();


    }


    @Scheduled(cron = "* * * ? * *")
    public void sendCrone() {

        getAllPending();
    }
}
