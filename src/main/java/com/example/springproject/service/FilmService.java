package com.example.springproject.service;

import com.example.springproject.model.Film;

import java.util.UUID;

public interface FilmService {
    Film getfilm(UUID filmUuid);
    Film savefilm(Film film);
}
