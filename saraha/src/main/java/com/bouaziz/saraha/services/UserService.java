package com.bouaziz.saraha.services;

import com.bouaziz.saraha.dto.UserDto;
import com.bouaziz.saraha.mappers.ObjectsMapper;
import com.bouaziz.saraha.models.User;
import com.bouaziz.saraha.repositories.UserRepository;
import com.bouaziz.saraha.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;
    private final ObjectsMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto save(UserDto userDto) {
        validator.validate(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User userToSave=mapper.toUser(userDto);
        if(userDto.getId()==null){//en mode creation
            String generatedUserName=generateUserName(userDto.getFirstname(),userDto.getLastname());
            userToSave.setUsername(generatedUserName);
        }

        User savedUser=repository.save(userToSave);
        return mapper.toUserDto(savedUser);
    }
    private String generateUserName(String firstName,String lastName) {
        return firstName + "-" + lastName+new Random().nextInt(1000);
    }
/*    public UserDto save(UserDto userDto){
        validator.validate(userDto);
        User userToSave=mapper.toUser(userDto);
        User savedUser=repository.save(userToSave);

    }*/

}
