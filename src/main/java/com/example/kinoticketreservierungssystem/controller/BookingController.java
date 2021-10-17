package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import com.example.kinoticketreservierungssystem.service.BookingProcess;
import com.example.kinoticketreservierungssystem.service.SeatingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/reservation")
@RestController
@CrossOrigin(origins = "*")
public class BookingController {


    @Autowired
    BookingProcess bookingProcess;
    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SeatingPlan seatingPlan;


    @PostMapping
    public Booking seatsReserved(@RequestBody List<String> seats, ShowEvent showEvent){
        return bookingProcess.reserveSeats(seats,showEvent);
    }

    @PutMapping("/successfulpayment")
    public Booking seatsBooked(@RequestBody Booking booking, Customer customer){
        return bookingProcess.bookSeats(booking, customer);
    }

}
