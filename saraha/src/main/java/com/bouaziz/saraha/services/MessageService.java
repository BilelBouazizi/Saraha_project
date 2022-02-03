package com.bouaziz.saraha.services;

import com.bouaziz.saraha.dto.MessageDto;
import com.bouaziz.saraha.exceptions.ObjectValidationException;
import com.bouaziz.saraha.mappers.ObjectsMapper;
import com.bouaziz.saraha.models.Message;
import com.bouaziz.saraha.models.Notification;
import com.bouaziz.saraha.models.User;
import com.bouaziz.saraha.repositories.MessageRepository;
import com.bouaziz.saraha.repositories.NotificationRepository;
import com.bouaziz.saraha.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //un constructeur avec un parametre final
public class MessageService {
    //On a pas besoin d’utiliser les interfaces d’un service
    private final MessageRepository repository;
    private final ObjectsValidator<MessageDto> validator;
    private final ObjectsMapper mapper;
    private final NotificationRepository notificationRepository;

    //envoyer un message
    public MessageDto save(MessageDto message) {
        //save object 1
        //save object 2 depends on object 2=>problem occured
        //appeler une API externe
        //save object 3 depends on object 1 and object 2

        //valdidation
        validator.validate(message);

        if(Objects.equals(message.getSenderId(), message.getReceiverId())){
            throw new ObjectValidationException(Set.of("sender and receiver should not be the same"),this.getClass().getName());
        }
        //mapping:MessageRepository(Message:qui n'est pas un messageDto)
        Message messageToSave = mapper.toMessage(message);
        messageToSave.setCreateDate(LocalDateTime.now());
        Message savedMessage = repository.save(messageToSave);

        createAndSaveNotification(message.getReceiverId());
        //save
        return mapper.toMessageDto(savedMessage);
    }

    //voir les messages envoyés
    public List<MessageDto> findAllSentMessagesByUser(Integer userId) {
return repository.findAllBySenderId(userId).stream()
        .map(mapper::toMessageDto)
        .collect(Collectors.toList());
    }

    //voir les messages reçs
    public List<MessageDto> findAllReceivedMessagesByUser(Integer userId) {
        return repository.findAllByReceiverId(userId).stream()
                .map(mapper::toMessageDto)
                .collect(Collectors.toList());
    }

    //partager rendre public un message
    public MessageDto publishMessage(Integer idMessage) {
        Message message = repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setPublicMsg(true);
        Message savedMessage = repository.save(message);

        return mapper.toMessageDto(savedMessage);
    }

    //ne pas partager rendre public un message
    public MessageDto unPublishMessage(Integer idMessage) {
        Message message = repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setPublicMsg(false);
        Message savedMessage = repository.save(message);

        return mapper.toMessageDto(savedMessage);

    }

    public MessageDto markAsFavorite(Integer idMessage) {
        Message message =repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setFavori(true);
        Message savedMessage = repository.save(message);
        return mapper.toMessageDto(savedMessage);
    }

    public MessageDto unmarkAsFavorite(Integer idMessage) {
        Message message =repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setFavori(false);
        Message savedMessage = repository.save(message);
        return mapper.toMessageDto(savedMessage);
    }
    public void createAndSaveNotification(Integer idReceiver){
        if(idReceiver == null){
            return ;
        }
        Notification notification=Notification.builder()
                .notification("You received new message at "+LocalDateTime.now())
                .createDate(LocalDateTime.now())
                .consulted(false)
                .user(User.builder()
                        .id(idReceiver)
                        .build())
                .build();
        notificationRepository.save(notification);
    }


}
