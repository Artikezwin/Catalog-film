package com.example.springproject.controller;

import com.example.springproject.model.UserEntity;
import com.example.springproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserEntity> getUser(@RequestParam UUID uuid) {
        UserEntity user = userService.getUser(uuid);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        UserEntity resulUser = userService.saveUser(user);
        return ResponseEntity.ok(resulUser);
    }
}
