package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.example.kinoticketreservierungssystem.entity.Movie;

import java.util.List;

public interface MovieRepository extends CosmosRepository<Movie, String> {


}
