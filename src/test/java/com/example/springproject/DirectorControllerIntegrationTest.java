package com.example.springproject;

import com.example.springproject.constant.GenreEnum;
import com.example.springproject.model.Director;
import com.example.springproject.model.Film;
import com.example.springproject.repository.DirectorRepository;
import com.example.springproject.service.DirectorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DirectorControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private DirectorService directorService;

    private static HttpHeaders httpHeaders;

    private void start() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public String baseURI() {
        return "http://localhost:" + port + "/director";
    }

    private String objectToJson(Director obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    private void clear(UUID directorUuid) {
        directorRepository.deleteById(directorUuid);
    }

    @Test
    public void testDirectorAdd() throws JsonProcessingException {
        start();

        Director director = new Director();
        director.setAge(2);
        director.setName("Раян Гослинг");
        director.setCountry("Казахстан");
        director.setFilmList(
                List.of(
                        new Film("Драйв", GenreEnum.ACTION, 10),
                        new Film("Слендермен", GenreEnum.HORROR, 4)
                )
        );

        HttpEntity<String> httpEntity = new HttpEntity<>(
                objectToJson(director),
                httpHeaders);
        ResponseEntity<Director> responseEntity = restTemplate.exchange(
                baseURI(),
                HttpMethod.POST,
                httpEntity,
                Director.class
        );
        Director directorFromResponse = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(directorFromResponse);

        assertThat(directorFromResponse.getFilmList().size()).isEqualTo(director.getFilmList().size());
        for (int i = 0; i < directorFromResponse.getFilmList().size(); i++) {
            assertThat(director.getFilmList().get(i).getTitle()).isEqualTo(directorFromResponse.getFilmList().get(i).getTitle());
            assertThat(director.getFilmList().get(i).getRating()).isEqualTo(directorFromResponse.getFilmList().get(i).getRating());
            assertThat(director.getFilmList().get(i).getGenre()).isEqualTo(directorFromResponse.getFilmList().get(i).getGenre());
        }

        Assertions.assertNotNull(directorRepository.findById(directorFromResponse.getUuid()));
        Assertions.assertNotNull(directorService.getDirector(directorFromResponse.getUuid()));

        assertThat(director.getName()).isEqualTo(directorFromResponse.getName());
        assertThat(director.getAge()).isEqualTo(directorFromResponse.getAge());
        assertThat(director.getCountry()).isEqualTo(directorFromResponse.getCountry());

        clear(directorFromResponse.getUuid());
    }

    @Test
    public void testGetDirectorByUuid() {
        Director director = new Director();
        director.setAge(2);
        director.setName("Раян Гослинг");
        director.setCountry("Казахстан");
        director.setFilmList(List.of(
                new Film("Драйв", GenreEnum.ACTION, 10),
                new Film("Слендермен", GenreEnum.HORROR, 4)
        ));
        directorRepository.save(director);

        ResponseEntity<Director> response =
                restTemplate.getForEntity(baseURI() + "?directorUuid={directorUuid}", Director.class, director.getUuid());
        Director directorFromResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(directorFromResponse).isEqualTo(director);

        clear(directorFromResponse.getUuid());
    }

}
