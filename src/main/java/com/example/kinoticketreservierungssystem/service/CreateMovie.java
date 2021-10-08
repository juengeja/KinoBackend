package com.example.kinoticketreservierungssystem.service;
/*
import com.azure.spring.data.cosmos.core.ReactiveCosmosTemplate;
import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class CreateMovie {



    private static ReactiveCosmosTemplate database1Template;

    private static MovieRepository movieRepository;

    @Autowired
    public void setDependencyA(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    public void setDependencyB(ReactiveCosmosTemplate database1Template) {
        this.database1Template = database1Template;
    }


    public static void createMovie(Movie movie){
        database1Template.upsert("Movies", movie).block();
        movieRepository.save(movie).block();


    }
}
*/