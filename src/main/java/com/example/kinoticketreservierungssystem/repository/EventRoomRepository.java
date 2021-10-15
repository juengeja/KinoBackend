package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.Cinema;
import com.example.kinoticketreservierungssystem.entity.EventRoom;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EventRoomRepository extends ReactiveCosmosRepository<EventRoom, String> {
    Mono<EventRoom> findByEventRoomID(String eventRoomID);
}
