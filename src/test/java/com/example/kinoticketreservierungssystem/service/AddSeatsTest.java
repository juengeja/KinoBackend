package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Cinema;
import com.example.kinoticketreservierungssystem.entity.EventRoom;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.repository.SeatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddSeatsTest {

    @Autowired
    SeatRepository seatRepository;
    @Autowired
    AddSeats addSeatsC;

    EventRoom eventRoom;
    Cinema cinemaInfo;

    List<Seat> seats;
    Seat seat1;
    Seat seat2;
    Seat seat3;
    Seat seat4;
    Seat seat5;
    Seat seat6;
    Seat seat7;
    Seat seat8;
    Seat seat9;

    Seat specificSeat;

    @Test
    void addSeats() {
        cinemaInfo = new Cinema("IndigoBW", "germany", "hesse", "heidelberg", "albrechtstraße", 12, 34567, "indigobw@gmail.com", "012345678901");
        eventRoom = new EventRoom("Belinda", "medium", cinemaInfo);

        seat1 = new Seat("BelindaA1", eventRoom, 'A', 1, false);
        seat2 = new Seat("BelindaA2", eventRoom, 'A', 2, false);
        seat3 = new Seat("BelindaA3", eventRoom, 'A', 3, false);
        seat4 = new Seat("BelindaB1", eventRoom, 'B', 1, false);
        seat5 = new Seat("BelindaB2", eventRoom, 'B', 2, false);
        seat6 = new Seat("BelindaB3", eventRoom, 'B', 3, false);
        seat7 = new Seat("BelindaC1", eventRoom, 'C', 1, false);
        seat8 = new Seat("BelindaC2", eventRoom, 'C', 2, false);
        seat9 = new Seat("BelindaC3", eventRoom, 'C', 3, false);

        seats = new ArrayList<>();

        seats.add(seat1);

        seats.add(seat2);

        seats.add(seat3);

        seats.add(seat4);

        seats.add(seat5);

        seats.add(seat6);

        seats.add(seat7);

        seats.add(seat8);

        seats.add(seat9);


        assertEquals(seats, addSeatsC.addSeats(eventRoom, 3, 3));

    }

    @Test
    void createSeats() {

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


        specificSeat = new Seat("TestA1", eventRoom, 'A', 1, false);

        addSeatsC.createSeats(seats);
        assertEquals(specificSeat, seatRepository.findBySeatID("TestA1").get());

    }

}
