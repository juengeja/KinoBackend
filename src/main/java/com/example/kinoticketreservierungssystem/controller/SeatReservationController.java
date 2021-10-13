package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.blSupport.SeatsSelected;
import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.service.BookingProcess;
import com.example.kinoticketreservierungssystem.service.SeatingPlan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/seatreservation")
@RestController
@CrossOrigin(origins = "*")
public class SeatReservationController{


    @GetMapping
    public Booking seatsReserved(@RequestBody List<Seat> seats, ShowEvent showEvent){
        return BookingProcess.reserveSeats(seats,showEvent);
    }


}
