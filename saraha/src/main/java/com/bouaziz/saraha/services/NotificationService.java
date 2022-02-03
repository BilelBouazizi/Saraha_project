package com.bouaziz.saraha.services;

import com.bouaziz.saraha.dto.NotificationDto;
import com.bouaziz.saraha.mappers.ObjectsMapper;
import com.bouaziz.saraha.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private ObjectsMapper mapper;

    public List<NotificationDto> findAllNotificationsByUser(Integer userId) {
       return repository.findAllByUserId(userId)
                .stream()
                .map(mapper::toNotificationDto)
                .collect(Collectors.toList());
    }
}


