package com.bouaziz.saraha.controller;

import com.bouaziz.saraha.dto.NotificationDto;
import com.bouaziz.saraha.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @GetMapping("/{user-id}")
     public ResponseEntity<List<NotificationDto>>findAllNotificationsByUser
            (@PathVariable ("user-id")Integer userId){
        return ResponseEntity.ok(service.findAllNotificationsByUser(userId));
    }


}
