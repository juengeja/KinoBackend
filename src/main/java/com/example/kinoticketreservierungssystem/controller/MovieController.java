package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Movie;
//import com.example.kinoticketreservierungssystem.service.CreateMovie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            Movie movie1 = new Movie();
            movie1.setMovieId(1);
            movie1.setName("The Aeronauts");
            movie1.setDomain("TheAeronauts");
            movie1.setGenre("Abenteuer, Biografie");
            movie1.setDuration(101);
            movie1.setRelease_date(LocalDate.of(2019, 12, 19));
            movie1.setMenu(true);
            movie1.setNight_event(false);
            movie1.setFeatured(false);
            movie1.setDescription("Ende des 19. Jahrhunderts: Die Luftfahrt-Enthusiastin Amelia Wren (Felicity Jones) liebt die Ballonafahrt über den Wolken, doch die gesellschaftlichen Konventionen der damaligen Zeit verbieten es einer Frau, Pilotin zu werden. Zeitgleich arbeitet der Wissenschaftler James Glaisher (Eddie Redmayne) daran, die Wettervorhersage zu verbessern und wird dafür für einen Fantasten gehalten. Die beiden gesellschaftlichen Außenseiter und Querdenker tun sich zusammen, um es der Welt zu zeigen. In einer halsbrecherischen, lebensgefährlichen Ballonfahrt über 8000 Meter wollen sie bisher Unerreichtes beweisen. Doch die beiden Abenteurer sind der Natur und den Gezeiten bald hoffnungslos ausgeliefert. Die Pionierarbeit ist wahrlich kein Zuckerschlecken ...");
            movie1.setTrailer("https://www.youtube.com/embed/tR4k2xKbASE");
            movie1.setImg("https://www.dvdsreleasedates.com/posters/800/T/The-Aeronauts-2019-movie-poster.jpg");
            movie1.setPresentation_date(presentation_dates);

            movies.add(movie1);

            Movie movie2 = new Movie();
            movie2.setMovieId(2);
            movie2.setName("Dune");
            movie2.setDomain("Dune");
            movie2.setGenre("Sci-Fi, Drama");
            movie2.setDuration(156);
            movie2.setRelease_date(LocalDate.of(2021, 9, 16));
            movie2.setMenu(true);
            movie2.setNight_event(false);
            movie2.setFeatured(true);
            movie2.setDescription("Paul Atreides (Timothee Chalamet) siedelt gemeinsam mit seinem Vater Herzog Leto (Oscar Isaac), seiner Mutter Lady Jessica (Rebecca Ferguson) und dem gesamten Hauststand des Adelshauses Atreides auf den Planeten Arrakis um, der auch als Dune bekannt ist. Dort sollen die Atreides sicherstellen, dass das Spice, eine Droge, die intergalaktische Reisen erst möglich macht und nur auf Arrakis zu finden ist, weiter abgebaut wird. Doch die Reise nach Arrakis entpuppt sich als Falle, die Baron Vladimir Harkonnen (Stellan Skarsgård) den Atreides gemeinsam mit dem Herrscher des galaktischen Imperiums gestellt hat. Paul muss gemeinsam mit seiner Mutter in die endlosen Wüsten von Dune fliehen, wo er auf die geheimnisvollen Fremen um deren Anführer Stilgar (Javier Bardem) und die furchtlose Chani (Zendaya) trifft, ein nomadisches Wüstenvolk, das auf die Ankunft eines prophezeiten Erlösers wartet...");
            movie2.setTrailer("https://www.youtube.com/embed/RYp8xMRaIMQ");
            movie2.setImg("https://alternativemovieposters.com/wp-content/uploads/2021/02/ClaireCurtis_Dune.jpg");
            movie2.setPresentation_date(presentation_dates);

            movies.add(movie2);

            Movie movie3 = new Movie();
            movie3.setMovieId(3);
            movie3.setName("James Bond 007 - Keine Zeit zu sterben");
            movie3.setDomain("JamesBond");
            movie3.setGenre("Action, Thriller, Spionage");
            movie3.setDuration(164);
            movie3.setRelease_date(LocalDate.of(2021, 9, 30));
            movie3.setMenu(true);
            movie3.setNight_event(true);
            movie3.setFeatured(true);
            movie3.setDescription("Eigentlich wollte James Bond (Daniel Craig) mit seiner großen Liebe Madeleine Swann (Léa Seydoux) seinen Ruhestand genießen und ein normales Leben führen. Doch Bonds alter Kumpel, CIA-Agent Felix Leiter (Jeffrey Wright), holt ihn zurück in sein altes Leben. Leiter braucht Bonds Hilfe, um einen entführten Wissenschaftler, Valdo Obruchev (David Dencik), zu retten. Die Mission erweist sich als heimtückisch und Bond muss bald erfahren, dass der so gefährliche wie mysteriöse Safin (Rami Malek) im Hintergrund die Strippen zieht. Safin verfügt über gefährliche neue Technologie. Ein letztes Mal muss Bond sich auch seinen Widersachern von Spectre stellen und dabei erkennen, dass Ernst Stavro Blofeld (Christoph Waltz) selbst aus dem Gefängnis heraus noch über Einfluss verfügt. Den neuen Gegner kann Bond nicht alleine besiegen und so braucht er unter anderem die Hilfe der neuen Doppel-Null-Agentin Nomi (Lashana Lynch) und der CIA-Agentin Paloma (Ana de Armas).");
            movie3.setTrailer("https://www.youtube.com/embed/EeQPNLFFe3k");
            movie3.setImg("https://gfx.videobuster.de/archive/v/cHyeG0HbWSnua07NozUhCVAcz0lMkawsSUyRjAxJTJGaW1hmSUyRmpwZWclMkbgv2RkrP02Y2E3ZWNh5WPzZGRmM2Q2ZmZmMy5qcGcmcj137zg/james-bond-007-keine-zeit-zu-sterben-poster.jpg");
            movie3.setPresentation_date(presentation_dates);

            movies.add(movie3);

            Movie movie4 = new Movie();
            movie4.setMovieId(4);
            movie4.setName("Candyman");
            movie4.setDomain("Candyman");
            movie4.setGenre("Action, Thriller, Spionage");
            movie4.setDuration(164);
            movie4.setRelease_date(LocalDate.of(2021, 9, 30));
            movie4.setMenu(true);
            movie4.setNight_event(true);
            movie4.setFeatured(false);
            movie4.setDescription("Als Anthony (Yahya Abdul-Mateen II) und seine Freundin Brianna (Teyonah Parris) in das Chicagoer Stadtviertel Cabrini Green ziehen, ahnen sie nicht, dass sie damit einen wahren Albtraum entfesseln. Von einem alten Bewohner der Gegend erfährt Anthony vom Fluch des sogenannten Candyman, einem unheimlichen Killer, der früher in Chicago sein Unwesen trieb, und dessen Name man fünfmal vor dem eigenen Spiegelbild aussprechen muss, um ihn heraufzubeschwören. Anthony ist fasziniert von der Legende um den unheimlichen Mörder. Doch je mehr er sich mit der Geschichte befasst, desto beunruhigender ist die Veränderung, die mit ihm vorgeht. Immer tiefer gerät Anthony in einen tödlichen Sog aus Wahn und Wirklichkeit, doch die alles entscheidende Frage ist: Wer ist der Candyman?");
            movie4.setTrailer("https://www.youtube.com/embed/qFXRFY5V8qY");
            movie4.setImg("https://i0.wp.com/teaser-trailer.com/wp-content/uploads/Candyman-movie-2021-poster.jpg?ssl=1");
            movie4.setPresentation_date(presentation_dates);

            movies.add(movie4);

            Movie movie5 = new Movie();
            movie5.setMovieId(4);
            movie5.setName("The Letter");
            movie5.setDomain("TheLetter");
            movie5.setGenre("Action, Thriller, Spionage");
            movie5.setDuration(164);
            movie5.setRelease_date(LocalDate.of(2021, 9, 30));
            movie5.setMenu(false);
            movie5.setNight_event(true);
            movie5.setFeatured(true);
            movie5.setDescription("The Letter ist ein Dokumentarfilm aus dem Jahr 2019 von Maia Lekow und Chris King.");
            movie5.setTrailer("https://www.youtube.com/embed/kxW-897DkSk");
            movie5.setImg("https://premium-films.com/sites/default/files/styles/large/public/poster_the-love-letter_low.jpg?itok=q5u9qOW9");

            movies.add(movie5);
        }

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}