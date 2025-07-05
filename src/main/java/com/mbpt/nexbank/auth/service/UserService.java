package com.mbpt.nexbank.auth.service;

import com.mbpt.nexbank.auth.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO verifyUser(UserDTO user);
}
