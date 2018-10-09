package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.TopicService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    
    @Override
    @Async
    public ResponseEntity<?> subscribe(TopicDTO topicDTO) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
                topicDTO.getDeviceTokens(),
                topicDTO.getTopic()
        );



        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Async
    public ResponseEntity<?> unsubscribe(TopicDTO topicDTO) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(
                topicDTO.getDeviceTokens(),
                topicDTO.getTopic()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
