package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.Query;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public interface ShowEventRepository extends ReactiveCosmosRepository<ShowEvent, String> {
    Mono<ShowEvent> findByShowEventID(String showEventID);

    @Query("SELECT distinct movieInfo FROM ShowEvents s WHERE s.isLive = true ORDER BY s.eventStart")
    Flux<ShowEvent> queryShowEventSchedule();

}
