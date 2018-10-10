package com.anuradha.fcmpushnotification.repository;

import com.anuradha.fcmpushnotification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
