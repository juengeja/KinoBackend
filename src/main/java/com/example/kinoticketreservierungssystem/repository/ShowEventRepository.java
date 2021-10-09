package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowEventRepository extends ReactiveCosmosRepository<ShowEvent, String> {

}
