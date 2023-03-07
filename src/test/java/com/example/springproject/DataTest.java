package com.example.springproject;

import com.example.springproject.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class DataTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void testFilmData(){
        assertNotNull(dataSource);
        assertNotNull(entityManager);
        assertNotNull(filmRepository);
    }
}
