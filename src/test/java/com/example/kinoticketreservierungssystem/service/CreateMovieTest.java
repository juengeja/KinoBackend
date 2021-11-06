package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateMovieTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CreateMovie createMovieC;

    Movie movie;

    @Test
    void createMovie() {
        movie = new Movie("Test-ID", "Test-Name", "Test-Genre", 164, "Test-Image", "Test-Description");
        createMovieC.createMovie(movie);
        assertEquals(movie, movieRepository.findByMovieId("Test-ID").get());

    }
}
