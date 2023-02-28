package com.example.springproject.controller;

import com.example.springproject.model.Film;
import com.example.springproject.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    @PostMapping
    public ResponseEntity<?> addfilm(@RequestBody Film film) {
        Film resulFilm = filmService.savefilm(film);
        return ResponseEntity.ok(resulFilm);
    }

    @GetMapping
    public ResponseEntity<?> getfilm(@RequestParam("filmUuid") UUID filmUuid) {
        Film film = filmService.getfilm(filmUuid);
        return ResponseEntity.ok(film);
    }
}
