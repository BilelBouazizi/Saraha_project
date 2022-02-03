package com.bouaziz.saraha.repositories;

import com.bouaziz.saraha.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
List<Notification> findAllByUserId(Integer id);
}
