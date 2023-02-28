package com.example.springproject.service.impl;

import com.example.springproject.model.Director;
import com.example.springproject.model.Film;
import com.example.springproject.repository.FilmRepository;
import com.example.springproject.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public Film getfilm(UUID filmUuid) {
        Optional<Film> filmOptional = filmRepository.findById(filmUuid);
        return filmOptional.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Film savefilm(Film film) {
        return filmRepository.save(film);
    }
}
