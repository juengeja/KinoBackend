package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.kinoticketreservierungssystem.entity.Admin;
import com.example.kinoticketreservierungssystem.entity.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CosmosRepository<Ticket, String> {

}