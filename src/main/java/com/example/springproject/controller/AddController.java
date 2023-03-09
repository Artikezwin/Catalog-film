package com.example.springproject.controller;

import com.example.springproject.model.Director;
import com.example.springproject.model.Film;
import com.example.springproject.service.DirectorService;
import com.example.springproject.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/add")
public class AddController {
    private final DirectorService directorService;
    private final FilmService filmService;

    @GetMapping("/director")
    public String newDirector(Model model) {
        model.addAttribute("newDirector", new Director());
        return "new-director";
    }

    @PostMapping("/new")
    public ResponseEntity<Director> addDirector(@ModelAttribute("newDirector") Director director) {
        return ResponseEntity.ok(directorService.saveDirector(director));
    }

//    @GetMapping("/film")
//    public String newFilmToDirector(Model model, @RequestParam UUID directorUuid) {
//        Director director = directorService.getDirector(directorUuid);
//        model.addAttribute("newFilm", new Film());
//        model.addAttribute("director", director);
//        return "new-film";
//    }
//
//    @PostMapping("/film-to-director")
//    public ResponseEntity<Film> addNewFilmToDirector(@ModelAttribute("newFilm") Film film, @ModelAttribute("director") Director director) {
//        Director directorMerged = director;
//        if (directorMerged.getFilmList() == null) {
//            directorMerged.setFilmList(
//                    List.of(
//                            film
//                    )
//            );
//        } else {
//            directorMerged.addFilm(film);
//        }
//        directorService.deleteDirector(director.getUuid());
//        directorService.saveDirector(directorMerged);
//        return ResponseEntity.ok(filmService.saveFilm(film));
//    }
}
