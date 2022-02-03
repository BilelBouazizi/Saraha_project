package com.bouaziz.saraha.mappers;

import com.bouaziz.saraha.dto.MessageDto;
import com.bouaziz.saraha.dto.NotificationDto;
import com.bouaziz.saraha.dto.UserDto;
import com.bouaziz.saraha.models.Message;
import com.bouaziz.saraha.models.Notification;
import com.bouaziz.saraha.models.User;
import org.springframework.stereotype.Component;

@Component
public class ObjectsMapper {
    public Message toMessage(MessageDto dto){
        if(dto==null){
            return null;
        }
        return Message.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .favori(dto.isFavori())
                .typeMsg(dto.getTypeMsg())
                .publicMsg(dto.isPublicMsg())
                .receiver(
                        User.builder()
                                .id(dto.getReceiverId())
                                .build()
                )
                .sender(
                        User.builder()
                                .id(dto.getSenderId())
                                .build()
                )
                .build();
    }
    public MessageDto toMessageDto(Message message){
        if(message==null){
            return null;
        }
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .createdDate(message.getCreateDate())
                .favori(message.isFavori())
                .typeMsg(message.getTypeMsg())
                .publicMsg(message.isPublicMsg())
                .senderId(message.getSender().getId())
                .receiverId(message.getReceiver().getId())
                .build();
    }
    public User toUser(UserDto userDto){
        if(userDto==null){
            return null;
        }
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstname())
                .lastName(userDto.getLastname())
                .birthDate(userDto.getBirthDate())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
    public UserDto toUserDto(User user){
        if(user==null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .username(user.getUsername())

                .build();
    }
    public NotificationDto toNotificationDto(Notification notification) {
        if(notification==null){
            return null;
        }
        return NotificationDto.builder()
                .id(notification.getId())
                .notification((notification.getNotification()))
                .createDate(notification.getCreateDate())
                .consulted(notification.isConsulted())
                .build();

    }
}
