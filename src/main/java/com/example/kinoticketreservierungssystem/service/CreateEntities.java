package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateEntities {

    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    EventRoomRepository eventRoomRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatingTemplateRepository seatingTemplateRepository;
    @Autowired
    CreateEntities createEntities;


    public void createCinema(String cinemaID, String country, String state, String city, String street, int houseNumber, int postalNumber, String businessEmail, String businessPhoneNumber) {
        cinemaRepository.save(new Cinema(cinemaID, country, state, city, street, houseNumber, postalNumber, businessEmail, businessPhoneNumber));
    }

    public void createEventRoom(String eventRoomID, String screenSize, Cinema cinemaInfo) {
        eventRoomRepository.save(new EventRoom(eventRoomID, screenSize, cinemaInfo));
    }

    public void createMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Cinema getCinema(String cinemaID){
        return cinemaRepository.findByCinemaID(cinemaID).get();
    }

    public EventRoom getEventRoom(String eventRoomID){
        return eventRoomRepository.findByEventRoomID(eventRoomID).get();
    }

    public List<Seat> getSeatsPerRoom(String eventRoomID){
        List<Seat> seatsPerRoomList = new ArrayList<Seat>();
        seatRepository.findAllByEventRoomInfo(createEntities.getEventRoom(eventRoomID)).forEach(seatsPerRoomList::add);
        return seatsPerRoomList;
    }
}
