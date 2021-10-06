package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
        if(movies.isEmpty()) {

            String[] presentation_dates = new String[4];
            presentation_dates[0] = "Montag 04.10.21 - 13:00";
            presentation_dates[1] = "Montag 04.10.21 - 17:00";
            presentation_dates[2] = "Dienstag 05.10.21 - 17:00";
            presentation_dates[3] = "Dienstag 05.10.21 - 21:00";



        }
        return new ResponseEntity<>(movies, HttpStatus.OK);

    }
}