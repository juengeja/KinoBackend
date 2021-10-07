package com.example.kinoticketreservierungssystem.repository;

import com.azure.cosmos.models.PartitionKey;
import com.example.kinoticketreservierungssystem.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieRepositoryImpl implements MovieRepository{
    @Override
    public List<Movie> listMovies(int movieName, String movieId) {
        return null;
    }

    @Override
    public MovieRepository getBean(Class<MovieRepository> movieRepositoryClass) {
        return null;
    }

    @Override
    public Optional<Movie> findById(String s, PartitionKey partitionKey) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String s, PartitionKey partitionKey) {

    }

    @Override
    public Iterable<Movie> findAll(PartitionKey partitionKey) {
        return null;
    }

    @Override
    public Iterable<Movie> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Movie> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Movie> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Movie> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Movie> findAll() {
        return null;
    }

    @Override
    public Iterable<Movie> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Movie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
