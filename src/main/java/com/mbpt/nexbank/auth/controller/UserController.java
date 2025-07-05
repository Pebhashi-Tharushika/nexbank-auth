package com.mbpt.nexbank.auth.controller;

import com.mbpt.nexbank.auth.dto.UserDTO;
import com.mbpt.nexbank.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping(value = "/verifyUser")
    public ResponseEntity<String> verifyUser(@RequestBody UserDTO user) {
        UserDTO userDTO = userService.verifyUser(user);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Exists. Please Sign Up");
        }
        if (Objects.equals(userDTO.getUsername(), user.getUsername()) && Objects.equals(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Valid user");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
    }
}
