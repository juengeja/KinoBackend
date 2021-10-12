package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.Cinema;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends ReactiveCosmosRepository<Cinema, String> {

}