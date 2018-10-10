package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return topicService.subscribe(topicDTO);

    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestBody TopicDTO topicDTO) {

        return topicService.unsubscribe(topicDTO);

    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/get-by-name/{topic}")
    public ResponseEntity<?> findTopicByName(@PathVariable String topic) {
        return topicService.findTopicByName(topic);
    }

}
