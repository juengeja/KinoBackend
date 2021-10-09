package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.EventRoom;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRoomRepository extends ReactiveCosmosRepository<EventRoom, String> {

}
