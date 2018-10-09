package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.TopicService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    @Override
    public ResponseEntity<?> subscribe(TopicDTO topicDTO) throws FirebaseMessagingException {
        return null;
    }

    @Override
    public ResponseEntity<?> unsubscribe(TopicDTO topicDTO) throws FirebaseMessagingException {
        return null;
    }
}
