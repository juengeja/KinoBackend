package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CosmosRepository<Booking, String> {

}
