package com.mbpt.nexbank.auth.service.impl;

import com.mbpt.nexbank.auth.dto.UserDTO;
import com.mbpt.nexbank.auth.entity.ScopeEntity;
import com.mbpt.nexbank.auth.entity.UserEntity;
import com.mbpt.nexbank.auth.repository.ScopeRepository;
import com.mbpt.nexbank.auth.repository.UserRepository;
import com.mbpt.nexbank.auth.service.UserService;
import com.mbpt.nexbank.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScopeRepository scopeRepository;

    @Override
    public String verifyUser(UserDTO userDTO) {
        final UserEntity userEntity = userRepository.findUserByUserName(userDTO.getUsername());
        if (userEntity != null && Objects.equals(userEntity.getUsername(), userDTO.getUsername()) && Objects.equals(userEntity.getPassword(), userDTO.getPassword())) {
            List<ScopeEntity> scopeEntityList = scopeRepository.findScopes(userEntity.getRoleId());
            final StringBuilder scopes = new StringBuilder();
            scopeEntityList.forEach(scope ->{
                scopes.append(scope.getPermission());
                scopes.append(",");
              }
            );
            return JwtUtil.generateToken(userDTO, scopes.toString());
        }
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        UserEntity savedUser = userRepository.save(entity);
        return new UserDTO(savedUser.getUsername(), savedUser.getPassword());
    }
}
