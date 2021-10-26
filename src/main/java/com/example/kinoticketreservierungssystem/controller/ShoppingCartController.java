package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.service.BookingProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/remove")
@RestController
@CrossOrigin(origins = "*")
public class ShoppingCartController {

    @Autowired
    BookingProcess bookingProcess;

    @PutMapping("{reservation}")
    public Booking backUndoReservation (@PathVariable String reservation){
        return bookingProcess.removeReservation(reservation);
    }
}
