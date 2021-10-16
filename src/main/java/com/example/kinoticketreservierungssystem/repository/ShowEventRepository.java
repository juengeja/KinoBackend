package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@Repository
public interface ShowEventRepository extends CosmosRepository<ShowEvent, String> {
    @Query("SELECT DISTINCT movieInfo FROM ShowEvents s WHERE s.isLive = true")
    Iterable<ShowEvent> queryShowEventSchedule();
    Optional<ShowEvent> findByShowEventID(String showEventID);
    Optional<ShowEvent> findBy(ShowEvent showEvent);
}
