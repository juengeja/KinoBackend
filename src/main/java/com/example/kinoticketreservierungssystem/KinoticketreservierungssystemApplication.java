package com.example.kinoticketreservierungssystem;

import com.example.kinoticketreservierungssystem.config.MovieRepositoryConfig;
import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import com.example.kinoticketreservierungssystem.repository.MovieRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import java.time.LocalDate;

@SpringBootApplication
public class KinoticketreservierungssystemApplication implements CommandLineRunner {

    @Autowired
    private MovieRepositoryImpl repository;
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(KinoticketreservierungssystemApplication.class, args);
    }

    public void run(String... var1){
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("The Aeronauts");
        movie1.setLiveStatus(true);
        movie1.setGenre("Abenteuer, Biografie");
        movie1.setDuration(101);
        movie1.setReleaseDate(LocalDate.of(2019, 12, 19));
        movie1.setDescription("Ende des 19. Jahrhunderts: Die Luftfahrt-Enthusiastin Amelia Wren (Felicity Jones) liebt die Ballonafahrt über den Wolken, doch die gesellschaftlichen Konventionen der damaligen Zeit verbieten es einer Frau, Pilotin zu werden. Zeitgleich arbeitet der Wissenschaftler James Glaisher (Eddie Redmayne) daran, die Wettervorhersage zu verbessern und wird dafür für einen Fantasten gehalten. Die beiden gesellschaftlichen Außenseiter und Querdenker tun sich zusammen, um es der Welt zu zeigen. In einer halsbrecherischen, lebensgefährlichen Ballonfahrt über 8000 Meter wollen sie bisher Unerreichtes beweisen. Doch die beiden Abenteurer sind der Natur und den Gezeiten bald hoffnungslos ausgeliefert. Die Pionierarbeit ist wahrlich kein Zuckerschlecken ...");

        repository.save(movie1);

        MovieRepositoryConfig bean =
                applicationContext.getBean(MovieRepositoryConfig.class);
        bean.switchToSecondaryKey();



    }

}
