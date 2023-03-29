package com.example.springproject.controller;

import com.example.springproject.model.Film;
import com.example.springproject.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    @PostMapping
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        Film resulFilm = filmService.saveFilm(film);
        return ResponseEntity.ok(resulFilm);
    }

    @GetMapping
    public ResponseEntity<Film> getFilm(@RequestParam("filmUuid") UUID filmUuid) {
        Film film = filmService.getFilm(filmUuid);
        return ResponseEntity.ok(film);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Film>> getFilms() {
        List<Film> film = filmService.getFilmList();
        return ResponseEntity.ok(film);
    }
}
