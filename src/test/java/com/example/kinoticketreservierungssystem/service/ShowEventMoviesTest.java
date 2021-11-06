package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import com.example.kinoticketreservierungssystem.repository.SeatingTemplateRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShowEventMoviesTest {

    @Autowired
    SeatingTemplateRepository seatingTemplateRepository;

    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ShowEventMovies showEventMovies;

    Movie movie1;
    Movie movie2;
    Movie movie3;
    Movie movie4;
    Movie movie5;

    Movie databaseMovie1;
    Movie databaseMovie2;
    Movie databaseMovie3;

    SeatingTemplate seatingTemplate;
    SeatingTemplate seatingTemplate2;
    SeatingTemplate seatingTemplate3;
    SeatingTemplate seatingTemplat4;


    ShowEvent firstEvent;
    ShowEvent secondEvent;
    ShowEvent thirdEvent;

    LocalDateTime eventStart;
    LocalDateTime eventStart2;
    LocalDateTime eventStart3;
    LocalDateTime eventStart4;+





    HashSet <Movie> movieSet;

    @Test
    void getAllShowEventMovies() {

        /*



        movie1 = new Movie("TheAeronauts",
                "The Aeronauts",
                "Action",
                101,
                "https://www.dvdsreleasedates.com/posters/800/T/The-Aeronauts-2019-movie-poster.jpg",
                "Ende des 19. Jahrhunderts: Die Luftfahrt-Enthusiastin Amelia Wren (Felicity Jones) liebt die Ballonafahrt über den Wolken, doch die gesellschaftlichen Konventionen der damaligen Zeit verbieten es einer Frau, Pilotin zu werden. Zeitgleich arbeitet der Wissenschaftler James Glaisher (Eddie Redmayne) daran, die Wettervorhersage zu verbessern und wird dafür für einen Fantasten gehalten. Die beiden gesellschaftlichen Außenseiter und Querdenker tun sich zusammen, um es der Welt zu zeigen. In einer halsbrecherischen, lebensgefährlichen Ballonfahrt über 8000 Meter wollen sie bisher Unerreichtes beweisen. Doch die beiden Abenteurer sind der Natur und den Gezeiten bald hoffnungslos ausgeliefert. Die Pionierarbeit ist wahrlich kein Zuckerschlecken ..."
        );

        movie2 = new Movie("Dune",
                "Dune",
                "Sci-Fi",
                156,
                "https://alternativemovieposters.com/wp-content/uploads/2021/02/ClaireCurtis_Dune.jpg",
                "Paul Atreides (Timothee Chalamet) siedelt gemeinsam mit seinem Vater Herzog Leto (Oscar Isaac), seiner Mutter Lady Jessica (Rebecca Ferguson) und dem gesamten Hauststand des Adelshauses Atreides auf den Planeten Arrakis um, der auch als Dune bekannt ist. Dort sollen die Atreides sicherstellen, dass das Spice, eine Droge, die intergalaktische Reisen erst möglich macht und nur auf Arrakis zu finden ist, weiter abgebaut wird. Doch die Reise nach Arrakis entpuppt sich als Falle, die Baron Vladimir Harkonnen (Stellan Skarsgård) den Atreides gemeinsam mit dem Herrscher des galaktischen Imperiums gestellt hat. Paul muss gemeinsam mit seiner Mutter in die endlosen Wüsten von Dune fliehen, wo er auf die geheimnisvollen Fremen um deren Anführer Stilgar (Javier Bardem) und die furchtlose Chani (Zendaya) trifft, ein nomadisches Wüstenvolk, das auf die Ankunft eines prophezeiten Erlösers wartet..."
        );

        movie3 = new Movie("JamesBond",
                "James Bond 007 - Keine Zeit zu sterben",
                "Action",
                164,
                "https://gfx.videobuster.de/archive/v/cHyeG0HbWSnua07NozUhCVAcz0lMkawsSUyRjAxJTJGaW1hmSUyRmpwZWclMkbgv2RkrP02Y2E3ZWNh5WPzZGRmM2Q2ZmZmMy5qcGcmcj137zg/james-bond-007-keine-zeit-zu-sterben-poster.jpg",
                "Eigentlich wollte James Bond (Daniel Craig) mit seiner großen Liebe Madeleine Swann (Léa Seydoux) seinen Ruhestand genießen und ein normales Leben führen. Doch Bonds alter Kumpel, CIA-Agent Felix Leiter (Jeffrey Wright), holt ihn zurück in sein altes Leben. Leiter braucht Bonds Hilfe, um einen entführten Wissenschaftler, Valdo Obruchev (David Dencik), zu retten. Die Mission erweist sich als heimtückisch und Bond muss bald erfahren, dass der so gefährliche wie mysteriöse Safin (Rami Malek) im Hintergrund die Strippen zieht. Safin verfügt über gefährliche neue Technologie. Ein letztes Mal muss Bond sich auch seinen Widersachern von Spectre stellen und dabei erkennen, dass Ernst Stavro Blofeld (Christoph Waltz) selbst aus dem Gefängnis heraus noch über Einfluss verfügt. Den neuen Gegner kann Bond nicht alleine besiegen und so braucht er unter anderem die Hilfe der neuen Doppel-Null-Agentin Nomi (Lashana Lynch) und der CIA-Agentin Paloma (Ana de Armas)..."
        );

        movie4 = new Movie("Candyman",
                "Candyman",
                "Action",
                164,
                "https://i0.wp.com/teaser-trailer.com/wp-content/uploads/Candyman-movie-2021-poster.jpg?ssl=1",
                "Als Anthony (Yahya Abdul-Mateen II) und seine Freundin Brianna (Teyonah Parris) in das Chicagoer Stadtviertel Cabrini Green ziehen, ahnen sie nicht, dass sie damit einen wahren Albtraum entfesseln. Von einem alten Bewohner der Gegend erfährt Anthony vom Fluch des sogenannten Candyman, einem unheimlichen Killer, der früher in Chicago sein Unwesen trieb, und dessen Name man fünfmal vor dem eigenen Spiegelbild aussprechen muss, um ihn heraufzubeschwören. Anthony ist fasziniert von der Legende um den unheimlichen Mörder. Doch je mehr er sich mit der Geschichte befasst, desto beunruhigender ist die Veränderung, die mit ihm vorgeht. Immer tiefer gerät Anthony in einen tödlichen Sog aus Wahn und Wirklichkeit, doch die alles entscheidende Frage ist: Wer ist der Candyman?"
        );

        movie5 = new Movie("TheLetter",
                "The Letter",
                "Action",
                164,
                "https://premium-films.com/sites/default/files/styles/large/public/poster_the-love-letter_low.jpg?itok=q5u9qOW9",
                "The Letter ist ein Dokumentarfilm aus dem Jahr 2019 von Maia Lekow und Chris King..."
        );

        movieSet = new HashSet<>();
        movieSet.add(movie1);
        movieSet.add(movie2);
        movieSet.add(movie3);
        movieSet.add(movie4);
        movieSet.add(movie5);


        // Methodenausgabe überprüfen
        assertEquals(movieSet, showEventMovies.getAllShowEventMovies());

        */



        // Untersuchen ob die einzelnen Show-Event Filme nicht leer sind

        databaseMovie1 = movieRepository.findByMovieId("JamesBond").get();
        assertFalse(databaseMovie1.getMovieName().isEmpty());

        databaseMovie2 = movieRepository.findByMovieId("TheLetter").get();
        assertFalse(databaseMovie2.getMovieName().isEmpty());

        databaseMovie3 = movieRepository.findByMovieId("Candyman").get();
        assertFalse(databaseMovie3.getMovieName().isEmpty());


    }

    @Test
    void getAllShowEventDates() {

        movie1 = movieRepository.findByMovieId("JamesBond").get();
        seatingTemplate = seatingTemplateRepository.findBySeatingTemplateID("AstraTemplate2021-10-24T21:54:43.795811400").get();
        eventStart = LocalDateTime.of(2021,
                12,
                24,
                16,
                0);

        seatingTemplate2 = seatingTemplateRepository.findBySeatingTemplateID("AstraTemplate2021-10-24T21:54:43.795811400").get();
        eventStart2 = LocalDateTime.of(2021,
                12,
                24,
                15,
                0);


        firstEvent = new ShowEvent("firstEvent", movie1, seatingTemplate,eventStart,true, true);
        secondEvent = new ShowEvent("secondEvent", movie1, seatingTemplate,eventStart,true, true);


        databaseMovie1 = movieRepository.findByMovieId("JamesBond").get();
        assertFalse(databaseMovie1.getMovieName().isEmpty());

        firstEvent = showEventRepository.findByShowEventID("firstEvent").get();
        assertTrue(firstEvent.getEventStart().isAfter(LocalDateTime.now(ZoneId.of("Europe/Berlin"))));

        secondEvent = showEventRepository.findByShowEventID("secondEvent").get();
        assertTrue(secondEvent.getEventStart().isAfter(LocalDateTime.now(ZoneId.of("Europe/Berlin"))));

        thirdEvent = showEventRepository.findByShowEventID("thirdEvent").get();
        assertTrue(thirdEvent.getEventStart().isAfter(LocalDateTime.now(ZoneId.of("Europe/Berlin"))));

        firstEvent = showEventRepository.findByShowEventID("firstEvent").get();
        assertFalse(firstEvent.getShowEventID().isEmpty());

        firstEvent = showEventRepository.findByShowEventID("firstEvent").get();
        assertFalse(firstEvent.getSeatingTemplateInfo().getSeatMap().isEmpty());

        secondEvent = showEventRepository.findByShowEventID("secondEvent").get();
        assertFalse(secondEvent.getShowEventID().isEmpty());

        secondEvent = showEventRepository.findByShowEventID("secondEvent").get();
        assertFalse(secondEvent.getSeatingTemplateInfo().getSeatMap().isEmpty());

        thirdEvent = showEventRepository.findByShowEventID("thirdEvent").get();
        assertFalse(thirdEvent.getShowEventID().isEmpty());

        thirdEvent = showEventRepository.findByShowEventID("thirdEvent").get();
        assertFalse(thirdEvent.getSeatingTemplateInfo().getSeatMap().isEmpty());













    }
}