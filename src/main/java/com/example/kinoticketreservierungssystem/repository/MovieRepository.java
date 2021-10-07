package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.example.kinoticketreservierungssystem.config.MovieRepositoryConfig;
import com.example.kinoticketreservierungssystem.entity.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CosmosRepository<Movie, String> {


    @Query("select * from c where c.movieName = @movieName and c.movieId = @movieId")
    List<Movie> listMovies(@Param("movieName") int movieName, @Param("movieId") String movieId);

    MovieRepository getBean(Class<MovieRepository> movieRepositoryClass);
}
