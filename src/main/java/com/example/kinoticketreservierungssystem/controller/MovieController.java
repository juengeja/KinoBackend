package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping()
@RestController
@CrossOrigin(origins = "*")
public class MovieController {

    private static List<Movie> movies = new ArrayList<>();

    @GetMapping("/moviedata")
    public ResponseEntity<Iterable<Movie>> getAllMovies(
    ) {
        Movie movie = new Movie();
        movie.setName("James Bond");
        movie.setDuration(162);
        movie.setDescription("Beschreibung des Films James Bond");
        movie.setGenre("Action");

        movies.add(movie);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<Iterable<Movie>> getAllMovies1(
    ) {
        Movie movie = new Movie();
        movie.setName("James Bond");
        movie.setDuration(170);
        movie.setDescription("Beschreibung des Films James Bond");
        movie.setGenre("Action");

        movies.add(movie);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

}