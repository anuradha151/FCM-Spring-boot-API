package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.TopicService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> createTopic(@RequestBody TopicDTO topicDTO) {
        try {
            return topicService.subscribe(topicDTO);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Topic creation failed...!", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestBody TopicDTO topicDTO) {
        try {
            return topicService.unsubscribe(topicDTO);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unsubscribe task failed...!", HttpStatus.BAD_REQUEST);
        }

    }
}
