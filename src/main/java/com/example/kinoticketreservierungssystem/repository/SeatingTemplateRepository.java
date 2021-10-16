package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SeatingTemplateRepository extends CosmosRepository<SeatingTemplate, String> {
}
