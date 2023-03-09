package com.example.springproject.service;

import com.example.springproject.model.Director;

import java.util.List;
import java.util.UUID;

public interface DirectorService {
    Director getDirector(UUID directorUuid);
    Director saveDirector(Director director);
    List<Director> getAll();
    void deleteDirector(UUID directorUuid);
}
