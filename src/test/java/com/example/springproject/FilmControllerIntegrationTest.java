package com.example.springproject;


import com.example.springproject.constant.GenreEnum;
import com.example.springproject.model.Film;
import com.example.springproject.repository.FilmRepository;
import com.example.springproject.service.FilmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    private static HttpHeaders httpHeaders;

    private void start() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    private void clear(UUID filmUuid) {
        filmRepository.deleteById(filmUuid);
    }

    public String baseURI() {
        return "http://localhost:" + port + "/film";
    }

    private String objectToJson(Film obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    @Test
    public void testAddFilm() throws JsonProcessingException {
        start();

        Film film = new Film("Слендер", GenreEnum.HORROR, 7);
        HttpEntity<String> httpEntity = new HttpEntity<>(objectToJson(film), httpHeaders);
        ResponseEntity<Film> response = restTemplate.exchange(
                baseURI(),
                HttpMethod.POST,
                httpEntity,
                Film.class
        );

        Film filmFromResponse = response.getBody();

        assertThat(film.getTitle()).isEqualTo(filmFromResponse.getTitle());
        assertThat(film.getGenre()).isEqualTo(film.getGenre());
        assertThat(film.getRating()).isEqualTo(filmFromResponse.getRating());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(filmService.getFilm(filmFromResponse.getUuid()));
        Assertions.assertNotNull(filmRepository.findById(filmFromResponse.getUuid()));

        clear(filmFromResponse.getUuid());
    }

    @Test
    public void getFilmById() {
        Film film = new Film("Слендер", GenreEnum.HORROR, 7);
        filmRepository.save(film);

        ResponseEntity<Film> response = restTemplate.getForEntity(
                baseURI() + "?filmUuid={filmUuid}",
                Film.class,
                film.getUuid());
        Film filmFromResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(film).isEqualTo(filmFromResponse);

        clear(filmFromResponse.getUuid());
    }
}
