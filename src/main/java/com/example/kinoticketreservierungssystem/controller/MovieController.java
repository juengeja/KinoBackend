package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Movie;
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
            Movie newMovie = new Movie();
            newMovie.setMovieId(1);
            newMovie.setName("The Aeronauts");
            newMovie.setDomain("The Aeronauts");
            newMovie.setGenre("Abenteuer, Biografie");
            newMovie.setDuration(101);
            newMovie.setRelease_date(LocalDate.of(2019, 12, 19));
            newMovie.setMenu(true);
            newMovie.setNight_event(false);
            newMovie.setFeatured(false);
            newMovie.setDescription("Ende des 19. Jahrhunderts: Die Luftfahrt-Enthusiastin Amelia Wren (Felicity Jones) liebt die Ballonafahrt über den Wolken, doch die gesellschaftlichen Konventionen der damaligen Zeit verbieten es einer Frau, Pilotin zu werden. Zeitgleich arbeitet der Wissenschaftler James Glaisher (Eddie Redmayne) daran, die Wettervorhersage zu verbessern und wird dafür für einen Fantasten gehalten. Die beiden gesellschaftlichen Außenseiter und Querdenker tun sich zusammen, um es der Welt zu zeigen. In einer halsbrecherischen, lebensgefährlichen Ballonfahrt über 8000 Meter wollen sie bisher Unerreichtes beweisen. Doch die beiden Abenteurer sind der Natur und den Gezeiten bald hoffnungslos ausgeliefert. Die Pionierarbeit ist wahrlich kein Zuckerschlecken ...");
            newMovie.setTrailer("https://www.youtube.com/embed/EeQPNLFFe3k");

            movies.add(newMovie);

            newMovie.setMovieId(2);
            newMovie.setName("Dune");
            newMovie.setDomain("Dune");
            newMovie.setGenre("Sci-Fi, Drama");
            newMovie.setDuration(156);
            newMovie.setRelease_date(LocalDate.of(2021, 9, 16));
            newMovie.setMenu(true);
            newMovie.setNight_event(false);
            newMovie.setFeatured(true);
            newMovie.setDescription("Paul Atreides (Timothee Chalamet) siedelt gemeinsam mit seinem Vater Herzog Leto (Oscar Isaac), seiner Mutter Lady Jessica (Rebecca Ferguson) und dem gesamten Hauststand des Adelshauses Atreides auf den Planeten Arrakis um, der auch als Dune bekannt ist. Dort sollen die Atreides sicherstellen, dass das Spice, eine Droge, die intergalaktische Reisen erst möglich macht und nur auf Arrakis zu finden ist, weiter abgebaut wird. Doch die Reise nach Arrakis entpuppt sich als Falle, die Baron Vladimir Harkonnen (Stellan Skarsgård) den Atreides gemeinsam mit dem Herrscher des galaktischen Imperiums gestellt hat. Paul muss gemeinsam mit seiner Mutter in die endlosen Wüsten von Dune fliehen, wo er auf die geheimnisvollen Fremen um deren Anführer Stilgar (Javier Bardem) und die furchtlose Chani (Zendaya) trifft, ein nomadisches Wüstenvolk, das auf die Ankunft eines prophezeiten Erlösers wartet...");
            newMovie.setTrailer("https://www.youtube.com/embed/EeQPNLFFe3k");

            movies.add(newMovie);

            newMovie.setMovieId(3);
            newMovie.setName("James Bond 007 - Keine Zeit zu sterben");
            newMovie.setDomain("James Bond 007 - Keine Zeit zu sterben");
            newMovie.setGenre("Action, Thriller, Spionage");
            newMovie.setDuration(164);
            newMovie.setRelease_date(LocalDate.of(2021, 9, 30));
            newMovie.setMenu(true);
            newMovie.setNight_event(true);
            newMovie.setFeatured(true);
            newMovie.setDescription("Eigentlich wollte James Bond (Daniel Craig) mit seiner großen Liebe Madeleine Swann (Léa Seydoux) seinen Ruhestand genießen und ein normales Leben führen. Doch Bonds alter Kumpel, CIA-Agent Felix Leiter (Jeffrey Wright), holt ihn zurück in sein altes Leben. Leiter braucht Bonds Hilfe, um einen entführten Wissenschaftler, Valdo Obruchev (David Dencik), zu retten. Die Mission erweist sich als heimtückisch und Bond muss bald erfahren, dass der so gefährliche wie mysteriöse Safin (Rami Malek) im Hintergrund die Strippen zieht. Safin verfügt über gefährliche neue Technologie. Ein letztes Mal muss Bond sich auch seinen Widersachern von Spectre stellen und dabei erkennen, dass Ernst Stavro Blofeld (Christoph Waltz) selbst aus dem Gefängnis heraus noch über Einfluss verfügt. Den neuen Gegner kann Bond nicht alleine besiegen und so braucht er unter anderem die Hilfe der neuen Doppel-Null-Agentin Nomi (Lashana Lynch) und der CIA-Agentin Paloma (Ana de Armas).");
            newMovie.setTrailer("https://www.youtube.com/embed/EeQPNLFFe3k");

            movies.add(newMovie);

            newMovie.setMovieId(4);
            newMovie.setName("Candyman");
            newMovie.setDomain("Candyman");
            newMovie.setGenre("Action, Thriller, Spionage");
            newMovie.setDuration(164);
            newMovie.setRelease_date(LocalDate.of(2021, 9, 30));
            newMovie.setMenu(true);
            newMovie.setNight_event(true);
            newMovie.setFeatured(false);
            newMovie.setDescription("Eigentlich wollte James Bond (Daniel Craig) mit seiner großen Liebe Madeleine Swann (Léa Seydoux) seinen Ruhestand genießen und ein normales Leben führen. Doch Bonds alter Kumpel, CIA-Agent Felix Leiter (Jeffrey Wright), holt ihn zurück in sein altes Leben. Leiter braucht Bonds Hilfe, um einen entführten Wissenschaftler, Valdo Obruchev (David Dencik), zu retten. Die Mission erweist sich als heimtückisch und Bond muss bald erfahren, dass der so gefährliche wie mysteriöse Safin (Rami Malek) im Hintergrund die Strippen zieht. Safin verfügt über gefährliche neue Technologie. Ein letztes Mal muss Bond sich auch seinen Widersachern von Spectre stellen und dabei erkennen, dass Ernst Stavro Blofeld (Christoph Waltz) selbst aus dem Gefängnis heraus noch über Einfluss verfügt. Den neuen Gegner kann Bond nicht alleine besiegen und so braucht er unter anderem die Hilfe der neuen Doppel-Null-Agentin Nomi (Lashana Lynch) und der CIA-Agentin Paloma (Ana de Armas).");
            newMovie.setTrailer("https://www.youtube.com/embed/EeQPNLFFe3k");

            movies.add(newMovie);
        }

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}