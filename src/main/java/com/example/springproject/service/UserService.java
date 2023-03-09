package com.example.springproject.service;

import com.example.springproject.model.UserEntity;

import java.util.UUID;

public interface UserService {
    UserEntity getUser(UUID uuid);
    UserEntity saveUser(UserEntity user);
}
