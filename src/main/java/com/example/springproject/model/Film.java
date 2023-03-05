package com.example.springproject.model;

import com.example.springproject.constant.GenreEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String title;
    private GenreEnum genre;
    private Integer rating;

    public Film(String title, GenreEnum genre, Integer rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public Film() {

    }
}
