package com.anuradha.fcmpushnotification.repository;

import com.anuradha.fcmpushnotification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.status = 'alive' ")
    List<Notification> getAllPending();

}
