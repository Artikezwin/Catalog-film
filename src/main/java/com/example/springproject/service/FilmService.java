package com.example.springproject.service;

import com.example.springproject.model.Film;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    Film getFilm(UUID filmUuid);
    Film saveFilm(Film film);
    List<Film> getFilmList();
}
