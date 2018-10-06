package com.anuradha.fcmpushnotification.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NotificationService {

    public static void main(String[] args) {
        new NotificationService().initializeFCM();
    }

    public void initializeFCM() {

        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("fcmtest-credentials.json");


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://fcmtest-c9511.firebaseio.com")
                    .build();

            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);


            System.out.println(firebaseApp);

            sendToTopic();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToTopic() {
        String topic = "springTopic05";
        try {
// See documentation on defining a message payload.

            Message message = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            // Time to live - 4 weeks default and maximum
                            .setTtl(3600 * 1000) //1 hour in milliseconds
                            .setPriority(AndroidConfig.Priority.NORMAL)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle("dodam gediya")
                                    .setBody("loku amu orange gediya")
                                    .setIcon("stock_ticker_update")

                                    .setColor("#9933ff")
                                    .build())
                            .build())
                    .setTopic("springTopic05")
                    .build();

// Send a message to the devices subscribed to the provided topic.
            String response = null;

            System.out.println(FirebaseMessaging.getInstance());

            response = FirebaseMessaging.getInstance().send(message);

// Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

}
