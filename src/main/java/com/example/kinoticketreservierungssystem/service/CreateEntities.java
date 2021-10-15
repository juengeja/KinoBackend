package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.CinemaRepository;
import com.example.kinoticketreservierungssystem.repository.EventRoomRepository;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import com.example.kinoticketreservierungssystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CreateEntities {

    private static CinemaRepository cinemaRepository;

    @Autowired
    public void setDependencyA(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    private static EventRoomRepository eventRoomRepository;

    @Autowired
    public void setDependencyB(EventRoomRepository eventRoomRepository) {
        this.eventRoomRepository = eventRoomRepository;
    }

    private static MovieRepository movieRepository;

    @Autowired
    public void setDependencyC(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    private static SeatRepository seatRepository;

    @Autowired
    public void setDependencyD(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    public static void createCinema(String cinemaID, String country, String state, String city, String street, int houseNumber, int postalNumber, String businessEmail, String businessPhoneNumber) {
        Cinema cinema = new Cinema(cinemaID, country, state, city, street, houseNumber, postalNumber, businessEmail, businessPhoneNumber);
        cinemaRepository.save(cinema).block();
    }

    public static void createEventRoom(String eventRoomID, String screenSize, Cinema cinemaInfo) {
        EventRoom eventRoom = new EventRoom(eventRoomID, screenSize, cinemaInfo);
        eventRoomRepository.save(eventRoom).block();
    }

    public static void createMovie(Movie movie) {
        movieRepository.save(movie).block();
    }

    public static Cinema getCinema(String cinemaID){
        Mono<Cinema> cinema = cinemaRepository.findByCinemaID(cinemaID);
        return cinema.block();
    }
    public static EventRoom getEventRoom(String eventRoomID){
        Mono<EventRoom> eventRoom = eventRoomRepository.findByEventRoomID(eventRoomID);
        return eventRoom.block();
    }

    public static List<Seat> getSeatsPerRoom(String eventRoomID){
        return seatRepository.findAllByEventRoomInfo(CreateEntities.getEventRoom(eventRoomID)).buffer().blockFirst();
    }
}
