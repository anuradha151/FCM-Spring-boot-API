package com.anuradha.fcmpushnotification.util;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Async
    @Scheduled(cron = "1 * * * *")
    public String send(Message message, Date date) throws FirebaseMessagingException {

        System.out.println("cron is running");
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
}
