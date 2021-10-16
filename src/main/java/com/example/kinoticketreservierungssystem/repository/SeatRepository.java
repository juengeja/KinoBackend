package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.EventRoom;
import com.example.kinoticketreservierungssystem.entity.Seat;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface SeatRepository extends CosmosRepository<Seat, String> {

    Iterable<Seat> findAllByEventRoomInfo(EventRoom eventRoom);

}
