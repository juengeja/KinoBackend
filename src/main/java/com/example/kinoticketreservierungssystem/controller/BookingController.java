package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import com.example.kinoticketreservierungssystem.service.BookingProcess;
import com.example.kinoticketreservierungssystem.service.SeatingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Booking> seatsReserved(@RequestBody Booking booking){
        return new ResponseEntity<Booking>( bookingProcess.reserveSeats(booking), HttpStatus.OK);
    }

    @PutMapping("/successfulpayment")
    public ResponseEntity<Booking> seatsBooked(@RequestBody Booking booking){
        return new ResponseEntity<Booking>( bookingProcess.bookSeats(booking), HttpStatus.OK);
    }

}
