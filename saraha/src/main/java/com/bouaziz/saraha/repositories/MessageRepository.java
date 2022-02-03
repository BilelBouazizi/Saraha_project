package com.bouaziz.saraha.repositories;

import com.bouaziz.saraha.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllBySenderId(Integer id);
    List<Message> findAllByReceiverId(Integer id);
}
