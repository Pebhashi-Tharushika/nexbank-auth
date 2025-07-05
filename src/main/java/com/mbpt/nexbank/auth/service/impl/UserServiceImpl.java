package com.mbpt.nexbank.auth.service.impl;

import com.mbpt.nexbank.auth.dto.UserDTO;
import com.mbpt.nexbank.auth.entity.UserEntity;
import com.mbpt.nexbank.auth.repository.UserRepository;
import com.mbpt.nexbank.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        UserEntity savedUser = userRepository.save(entity);
        return new UserDTO(savedUser.getUsername(), savedUser.getPassword());
    }

    @Override
    public UserDTO verifyUser(UserDTO user) {
        UserEntity userEntity = userRepository.findUserByUserName(user.getUsername());
        return new UserDTO(userEntity.getUsername(), userEntity.getPassword());
    }
}
