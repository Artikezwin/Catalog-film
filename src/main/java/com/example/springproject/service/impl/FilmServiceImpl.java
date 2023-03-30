package com.example.springproject.service.impl;

import com.example.springproject.model.Film;
import com.example.springproject.repository.FilmRepository;
import com.example.springproject.service.FilmService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Timeout;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    @Override
    public Film getFilm(UUID filmUuid) {
        Optional<Film> filmOptional = filmRepository.findById(filmUuid);
        return filmOptional.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @CircuitBreaker(name = "getFilmList", fallbackMethod = "getFilmListFallback")
    @Override
    public List<Film> getFilmList() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return filmRepository.findAll();
    }

    public List<Film> getFilmListFallback(final Throwable t) {
        log.error("Exception happened, falling to fallback method. Exception " + t.getMessage());
        return new ArrayList<>();
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }
}
