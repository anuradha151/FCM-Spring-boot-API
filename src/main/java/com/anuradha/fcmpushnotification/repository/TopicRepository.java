package com.anuradha.fcmpushnotification.repository;

import com.anuradha.fcmpushnotification.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
