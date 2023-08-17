package com.example.muzfi.Repository;

import com.example.muzfi.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByUserId(Integer userId);
}
