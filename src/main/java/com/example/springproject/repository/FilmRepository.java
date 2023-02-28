package com.example.springproject.repository;

import com.example.springproject.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {
}
