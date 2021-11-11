package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.Reservation;
import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class BookingProcessTest{

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
    Reservation newReservation;
    Booking booking;
    Booking newBooking;
    ShowEvent showEvent;
    private final CountDownLatch countDown = new CountDownLatch(1);


    @Test
    void reserveSeats() {

       // newReservation = new Reservation("ReserveSeats-Reservationstest");
        booking = new Booking("ReserveSeats-Bookingtest", false);
        bookingRepository.save(booking);


        assertEquals(booking,  bookingProcess.reserveSeats(reservation));
        assertFalse(reservation.getBookingInfo() == null);
        assertFalse(reservation.getShowEventInfo() == null);
    }

    @Test
    void seatsReservedTimer() {
        newReservation = new Reservation("ReservedTimer-Reservationtest", "Showevent-ID", "ReservedTimer-Bookingtest", "Eventtest-IDC1", 8);
        newBooking = new Booking("ReservedTimer-Bookingtest", true, "reserved", newReservation, 8);
        bookingRepository.save(newBooking);

        showEvent = showEventRepository.findByShowEventID("Showevent-ID").get();
        showEvent.getSeatingTemplateInfo().getSeatMap().get("Eventtest-IDC1").setBooked(true);
        showEventRepository.save(showEvent);

        assertTrue(showEventRepository.findByShowEventID("Showevent-ID").get().getSeatingTemplateInfo().getSeatMap().get("Eventtest-IDC1").isBooked());

        try {
            bookingProcess.seatsReservedTimer(newReservation, 1);
            countDown.await(1 * 2, TimeUnit.MILLISECONDS);
            assertFalse(newBooking.getBookingStatus() == "reserved");
            assertFalse(showEventRepository.findByShowEventID("Showevent-ID").get().getSeatingTemplateInfo().getSeatMap().get("Eventtest-IDC1").isBooked());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void bookSeats() {
    }

    @Test
    void removeReservation() {
    }
}