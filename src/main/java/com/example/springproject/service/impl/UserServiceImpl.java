package com.example.springproject.service.impl;

import com.example.springproject.model.UserEntity;
import com.example.springproject.repository.UserRepository;
import com.example.springproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public UserEntity getUser(UUID uuid) {
        Optional<UserEntity> userOptional = userRepository.findById(uuid);
        return userOptional.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
