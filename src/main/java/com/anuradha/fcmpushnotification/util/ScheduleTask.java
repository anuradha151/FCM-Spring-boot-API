package com.anuradha.fcmpushnotification.util;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class ScheduleTask {

    private static Message message = null;
    private static Date date = null;

    private static int count = 0;


    public String send(Message message, Date date) throws FirebaseMessagingException {

        this.message = message;
        this.date = date;

        this.count = 1;

        System.out.println("send method");

//        System.out.println("cron is running");
//        FirebaseMessaging.getInstance().send(this.message);
//        Date nowDate = new Date();
//        if (nowDate.equals(date)) {

//            System.out.println("message send method");
//
//            return FirebaseMessaging.getInstance().send(message);
//        }
//        sendCrone();
        return "alive";

    }

    @Scheduled(cron = "* * * ? * *")
    public void sendCrone() {

        System.out.println("cron is running : " + count);
//        try {
        if (message != null) {
//                FirebaseMessaging.getInstance().send(this.message);
            count++;
        }

//        } catch (FirebaseMessagingException e) {
//            e.printStackTrace();
//        }
    }
}
