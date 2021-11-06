package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.Reservation;
import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingProcessTest extends Thread {

    @Autowired

    BookingProcess bookingProcess;
    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SeatingPlan seatingPlan;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ReservationRepository reservationRepository;


    Reservation reservation;
    Booking booking;
    Booking bookingDatabase;

    @Test
    void reserveSeats() {
        booking = new Booking("Booking2021-10-26T10:00:05.906355", false);
        bookingDatabase = bookingRepository.findByBookingID("Booking2021-10-26T10:00:05.906355").get();

        reservation = reservationRepository.findById("Tue Oct 26 2021 10:02:37 GMT+0200 (Central European Summer Time)").get();


        assertEquals(booking,  bookingProcess.reserveSeats(reservation));
        assertFalse(reservation.getBookingInfo() == null);
        assertFalse(reservation.getShowEventInfo() == null);



    }

    @Test
    void seatsReservedTimer()  {
        reservation = reservationRepository.findById("Tue Oct 26 2021 10:02:37 GMT+0200 (Central European Summer Time)").get();
        booking = bookingRepository.findByBookingID("Booking2021-10-26T10:00:05.906355").get();

        assertTrue(booking.getBookingStatus() == "reserved");
        try {
            bookingProcess.seatsReservedTimer(reservation);
            Thread.sleep(324000000);
        }catch(Exception e){
        }
        assertFalse(reservation.getSeats().contains("AstraG9"));




    }

    @Test
    void bookSeats() {
    }

    @Test
    void removeReservation() {
    }
}
