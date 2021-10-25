package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.kinoticketreservierungssystem.blSupport.Reservation;
import com.example.kinoticketreservierungssystem.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CosmosRepository<Reservation, String> {

}
