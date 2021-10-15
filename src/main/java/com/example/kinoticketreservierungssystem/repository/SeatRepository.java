package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.EventRoom;
import com.example.kinoticketreservierungssystem.entity.Seat;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SeatRepository extends ReactiveCosmosRepository<Seat, String> {

    Flux<Seat> findAllByEventRoomInfo(EventRoom eventRoom);
}
