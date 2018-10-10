package com.anuradha.fcmpushnotification.repository;

import com.anuradha.fcmpushnotification.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t WHERE t.topic = :topic")
    Optional<Topic> findByName(@Param("topic") String topic);

}
