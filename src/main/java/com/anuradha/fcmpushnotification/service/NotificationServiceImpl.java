package com.anuradha.fcmpushnotification.service;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.model.ResponseModel;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class NotificationServiceImpl {


    public ResponseEntity<?> initializeFCM() {

        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("fcmtest-credentials.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://fcmtest-c9511.firebaseio.com")
                    .build();

            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);

            System.out.println("\n\nFirebaseApp  : " + firebaseApp + "\n\n");


            sendToTopic(new NotificationDTO());


            return new ResponseEntity<>("Service stated", HttpStatus.OK);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Service failed to start", HttpStatus.EXPECTATION_FAILED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Service failed to start", HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<?> sendToTopic(NotificationDTO notificationDTO) {
        String topic = "springTopic05";
        String response = null;
        try {
            // See documentation on defining a message payload.

            Message message = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            // Time to live - 4 weeks default and maximum
                            .setTtl(3600 * 1000) //1 hour in milliseconds
                            .setPriority(AndroidConfig.Priority.NORMAL)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle("Pavithra")
                                    .setBody("Homie")
                                    .setIcon("stock_ticker_update")

                                    .setColor("#0066ff")
                                    .build())
                            .build())
                    .setTopic("springTopic05")
                    .build();
            // Send a message to the devices subscribed to the provided topic.


            System.out.println(FirebaseMessaging.getInstance());

            response = FirebaseMessaging.getInstance().send(message);


            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);

            // Create Response
            ResponseModel res = new ResponseModel(HttpStatus.OK.value(), "Notification sent successfully", true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            ResponseModel res = new ResponseModel(HttpStatus.BAD_REQUEST.value(), "Message sending failed..", false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }

}
