package com.anuradha.fcmpushnotification.service.impl;

import com.anuradha.fcmpushnotification.dto.TopicDTO;
import com.anuradha.fcmpushnotification.dto.UserDTO;
import com.anuradha.fcmpushnotification.model.Topic;
import com.anuradha.fcmpushnotification.model.User;
import com.anuradha.fcmpushnotification.repository.TopicRepository;
import com.anuradha.fcmpushnotification.service.TopicService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    @Async
    public ResponseEntity<?> subscribe(TopicDTO topicDTO) {

        if (topicDTO == null) {
            return new ResponseEntity<>("Topic details unavailable", HttpStatus.BAD_REQUEST);
        }

        Optional<Topic> byName = topicRepository.findByName(topicDTO.getTopic());
        if (byName.isPresent()) {
            return new ResponseEntity<>("Existing topic. Please change the topic", HttpStatus.BAD_REQUEST);
        }

        TopicManagementResponse response = null;
        try {
            List<String> deviceTokens = new ArrayList<>();
            List<UserDTO> userDTOS = topicDTO.getUserDTOS();
            for (UserDTO userDTO : userDTOS) {
                deviceTokens.add(userDTO.getDeviceToken());
            }
            response = FirebaseMessaging.getInstance().subscribeToTopic(
                    deviceTokens,
                    topicDTO.getTopic()
            );
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Topic creation failed due to firebase server error", HttpStatus.EXPECTATION_FAILED);
        }

        Topic save = topicRepository.save(dTOToEntity(topicDTO));
        if (save == null) {
            return new ResponseEntity<>("Topic saving failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Async
    public ResponseEntity<?> unsubscribe(TopicDTO topicDTO) {
        TopicManagementResponse response = null;
        try {
            response = FirebaseMessaging.getInstance().unsubscribeFromTopic(
                    topicDTO.getDeviceTokens(),
                    topicDTO.getTopic()
            );
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("topic unsubscription failed due to firebase server error", HttpStatus.EXPECTATION_FAILED);
        }
//        topicRepository.dele

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllTopics() {
        List<Topic> all = topicRepository.findAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>("No topics available in server. Please create one.", HttpStatus.NO_CONTENT);
        }
        List<TopicDTO> topicDTOS = new ArrayList<>();

        for (Topic topic : all) {
            TopicDTO topicDTO = entityToDTO(topic);
            topicDTOS.add(topicDTO);
        }
        return new ResponseEntity<>(topicDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findTopicByName(String topic) {
        Optional<Topic> byName = topicRepository.findByName(topic);
        if (byName.isPresent()) {
            Topic topic1 = byName.get();
            TopicDTO topicDTO = entityToDTO(topic1);
            return new ResponseEntity<>(topicDTO, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("The topic you asked for is unavailable.", HttpStatus.NO_CONTENT);

    }

    private Topic dTOToEntity(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setTopic(topic.getTopic());
        List<User> users = new ArrayList<>();
        for (UserDTO userDTOS : topicDTO.getUserDTOS()) {
            User user = new User();
            user.setUser_id(userDTOS.getUser_id());
            user.setName(userDTOS.getName());
            user.setDeviceToken(userDTOS.getDeviceToken());
            users.add(user);
        }
        topic.setUsers(users);
        return topic;

    }

    private TopicDTO entityToDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopic(topic.getTopic());
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : topic.getUsers()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(user.getUser_id());
            userDTO.setName(user.getName());
            userDTO.setDeviceToken(user.getDeviceToken());

            userDTOS.add(userDTO);
        }
        topicDTO.setUserDTOS(userDTOS);

        return topicDTO;
    }
}
