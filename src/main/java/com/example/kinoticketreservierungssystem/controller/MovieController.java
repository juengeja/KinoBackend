package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.service.CreateMovie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/moviedata")
@RestController
@CrossOrigin(origins = "*")
public class MovieController {

    private static List<Movie> movies = new ArrayList<>();

    @PostMapping
    public void createMovie(@RequestBody Movie movie) {
        CreateMovie.createMovie(movie);
    }

    @GetMapping("/moviedata")
    public ResponseEntity<Iterable<Movie>> getAllMovies(
    ) {
        if(movies.isEmpty()) {

            String[] presentation_dates = new String[4];
            presentation_dates[0] = "Montag 04.10.21 - 13:00";
            presentation_dates[1] = "Montag 04.10.21 - 17:00";
            presentation_dates[2] = "Dienstag 05.10.21 - 17:00";
            presentation_dates[3] = "Dienstag 05.10.21 - 21:00";

            Movie movie1 = new Movie();
            movie1.setMovieId("1");
            movie1.setMovieName("The Aeronauts");
            movie1.setLiveStatus(true);
            movie1.setMainGenre("Action");
            movie1.setDuration(101);
            movie1.setDescription("Ende des 19. Jahrhunderts: Die Luftfahrt-Enthusiastin Amelia Wren (Felicity Jones) liebt die Ballonafahrt über den Wolken, doch die gesellschaftlichen Konventionen der damaligen Zeit verbieten es einer Frau, Pilotin zu werden. Zeitgleich arbeitet der Wissenschaftler James Glaisher (Eddie Redmayne) daran, die Wettervorhersage zu verbessern und wird dafür für einen Fantasten gehalten. Die beiden gesellschaftlichen Außenseiter und Querdenker tun sich zusammen, um es der Welt zu zeigen. In einer halsbrecherischen, lebensgefährlichen Ballonfahrt über 8000 Meter wollen sie bisher Unerreichtes beweisen. Doch die beiden Abenteurer sind der Natur und den Gezeiten bald hoffnungslos ausgeliefert. Die Pionierarbeit ist wahrlich kein Zuckerschlecken ...");

            movies.add(movie1);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);

    }
}