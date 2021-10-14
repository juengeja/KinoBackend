package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Cinema;
import com.example.kinoticketreservierungssystem.repository.CinemaRepository;
import com.example.kinoticketreservierungssystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCinemaAndRooms {

    private static CinemaRepository cinemaRepository;
    @Autowired
    public void setDependencyA(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public static void createCinema(){
        Cinema cinema = new Cinema(IndigoBW,);
    }
}
