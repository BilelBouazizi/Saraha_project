package com.bouaziz.saraha.controller;

import com.bouaziz.saraha.dto.MessageDto;
import com.bouaziz.saraha.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(value="**",maxAge = 3600)
@RequestMapping("/messages")//issm ressource à la fin 's':pluriels
public class MessageController {
    //spring va injecter grace @RequiredArgsConstructor
    private final MessageService service;

    @PostMapping("/")
    public ResponseEntity<MessageDto> save(@RequestBody MessageDto message) {
        return ResponseEntity.ok(service.save(message));
    }
@GetMapping("/received/{user-id}")
    public ResponseEntity<List<MessageDto>> findAllSentMessagesByUser(@PathVariable(name="user-id") Integer userId) {
        return ResponseEntity.ok(service.findAllReceivedMessagesByUser(userId));
    }
    @GetMapping("/sent/{user-id}")
    public ResponseEntity<List<MessageDto>> findAllReceivedMessagesByUser(@PathVariable(name="user-id") Integer userId) {
        return ResponseEntity.ok(service.findAllSentMessagesByUser(userId));
    }

    @PatchMapping("/publish/{id-message}")
    public ResponseEntity<MessageDto> publishMessage(@PathVariable(name="id-message") Integer idMessage) {
        return ResponseEntity.ok(service.publishMessage(idMessage));
    }
    @PatchMapping("/unpublish/{id-message}")
    public ResponseEntity<MessageDto> unPublishMessage(@PathVariable(name="id-message") Integer idMessage) {
        return ResponseEntity.ok(service.unPublishMessage(idMessage));
    }
    @PatchMapping("/mark-as-fav/{id-message}")
    public ResponseEntity<MessageDto> markAsFavorite(@PathVariable(name="id-message") Integer idMessage) {
        return ResponseEntity.ok(service.markAsFavorite(idMessage));
    }
    @PatchMapping("/unmark-as-fav/{id-message}")
    public ResponseEntity<MessageDto> unmarkAsFavorite(@PathVariable(name="id-message")Integer idMessage) {
        return ResponseEntity.ok(service.unmarkAsFavorite(idMessage));
    }
}

