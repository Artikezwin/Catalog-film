package com.example.springproject.controller;

import com.example.springproject.model.Director;
import com.example.springproject.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/director")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;
    @PostMapping
    public ResponseEntity<Director> addDirector(@RequestBody Director director) {
        Director resultDirector = directorService.saveDirector(director);
        return ResponseEntity.ok(resultDirector);
    }

    @GetMapping
    public ResponseEntity<Director> getDirector(@RequestParam("directorUuid") UUID directorUuid) {
        Director director = directorService.getDirector(directorUuid);
        return ResponseEntity.ok(director);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Director>> getDirectors() {
        List<Director> directors = directorService.getAll();
        return ResponseEntity.ok(directors);
    }
}
