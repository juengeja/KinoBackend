package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateEntitiesTest {

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
    @Autowired
    ShowEventRepository showEventRepository;

    Cinema cinema;
    Cinema cinemaInfo;
    EventRoom eventRoom;
    ShowEvent showEvent;
    SeatingTemplate seatingTemplate;
    LocalDateTime eventStart;
    Map<String, SeatMod> seatMap;
    SeatMod seatMod;
    Movie movie;
    List<Seat> seats;
    Seat seat1;
    Seat seat2;
    Seat seat3;
    Seat seat4;
    Seat seat5;
    Seat seat6;

    @Test
    void createCinema() {
        cinema = new Cinema("Test-ID", "Test-Country","Test-State","Test-City", "Test-Street", 123,4567,"Test-Mail","Test-PhoneNumber");
        createEntities.createCinema("Test-ID", "Test-Country", "Test-State", "Test-City", "Test-Street", 123, 4567, "Test-Mail", "Test-PhoneNumber");
        assertEquals(cinema, cinemaRepository.findByCinemaID("Test-ID").get());

    }

    @Test
    void createEventRoom() {
        cinema = new Cinema("Test-ID", "Test-Country","Test-State","Test-City", "Test-Street", 123,4567,"Test-Mail","Test-PhoneNumber");
        eventRoom = new EventRoom("Test-ID","Test-Size", cinema);
        createEntities.createEventRoom("Test-ID", "Test-Size", cinema);
        assertEquals(eventRoom, eventRoomRepository.findByEventRoomID("Test-ID").get());

    }


    @Test
    void getCinema() {
        cinema = new Cinema("Test-ID", "Test-Country","Test-State","Test-City", "Test-Street", 123,4567,"Test-Mail","Test-PhoneNumber");
        assertEquals(cinema, createEntities.getCinema("Test-ID"));

    }

    @Test
    void getEventRoom() {
        cinema = new Cinema("Test-ID", "Test-Country","Test-State","Test-City", "Test-Street", 123,4567,"Test-Mail","Test-PhoneNumber");
        eventRoom = new EventRoom("Test-ID","Test-Size", cinema);
        assertEquals(eventRoom, createEntities.getEventRoom("Test-ID"));
    }

    /* @Test
    void getSeatsPerRoom() {
    cinemaInfo = new Cinema("IndigoBW", "germany", "hesse", "heidelberg", "albrechtstraße", 12, 34567, "indigobw@gmail.com", "012345678901");
    eventRoom = new EventRoom("Test", "medium", cinemaInfo);

        seat1 = new Seat("TestA1", eventRoom, 'A', 1, false);
        seat2 = new Seat("TestA2", eventRoom, 'A', 2, false);
        seat3 = new Seat("TestA3", eventRoom, 'A', 3, false);
        seat4 = new Seat("TestB1", eventRoom, 'B', 1, false);
        seat5 = new Seat("TestB2", eventRoom, 'B', 2, false);
        seat6 = new Seat("TestB3", eventRoom, 'B', 3, false);

        seats = new ArrayList<>();
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
        seats.add(seat4);
        seats.add(seat5);
        seats.add(seat6);

        assertEquals(seats, createEntities.getSeatsPerRoom("Test"));
    }*/

    @Test
    void createSeatingTemplate() {

        seatingTemplate = seatingTemplateRepository.findBySeatingTemplateID("Test-IDTemplate2021-10-31T13:53:44.875012800").get();
        assertTrue(seatingTemplate.getEventRoomID().equals("Test-ID"));
    }

    @Test
    void createShowEvent() {
        seatingTemplate = seatingTemplateRepository.findBySeatingTemplateID("Test-IDTemplate2021-10-31T13:53:44.875012800").get();
        eventStart = LocalDateTime.of(2021,10,31,14,10);
        movie = new Movie("Test-ID", "Test-Name", "Test-Genre", 164, "Test-Image", "Test-Description");
        showEvent = new ShowEvent("Test-ID", movie, seatingTemplate, eventStart, true, false );

        createEntities.createShowEvent("Test-ID","Test-ID", "Test-IDTemplate2021-10-31T13:53:44.875012800", eventStart, true, false);
        assertEquals(showEvent, showEventRepository.findByShowEventID("Test-ID").get());

    }


}
