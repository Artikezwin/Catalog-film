package com.example.springproject.controller;

import com.example.springproject.model.Director;
import com.example.springproject.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class ViewerController {
    private final DirectorService directorService;

    @GetMapping("/all")
    public String viewAll(Model model) {
        List<Director> directors = directorService.getAll();
        model.addAttribute("listOfDirectors", directors);
        return "directors";
    }
}
