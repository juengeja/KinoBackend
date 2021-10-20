package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.Reservation;
import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
import com.example.kinoticketreservierungssystem.repository.CustomerRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.Semaphore;

@Service
public class BookingProcess {

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


    private static Semaphore semaphore;

    public BookingProcess(){
        semaphore = new Semaphore(1);
    }

    public Booking reserveSeats(Reservation reservation) {
        ShowEvent showEvent = showEventRepository.findByShowEventID(reservation.getShowEventInfo()).get();
        Set<String> seats = reservation.getSeats();
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        Booking booking = new Booking("Booking"+creationDateTime);
        int totalAmount = 0;
        try {
            semaphore.acquire();
            Set<String> seatsAdded = new HashSet<>();
            for(String seat:seats){
                if(showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).isBooked()==true){
                    booking.setBookingStatus("denied");
                    seatingPlan.deselectSeats(seatsAdded, showEvent);
                    break;
            } else{seatsAdded.add(seat);
                    totalAmount += showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).getPrice();
                    seatingPlan.selectSeats(seatsAdded, showEvent);
                    booking.setBookingStatus("reserved");
                    booking.setTotalPrice(totalAmount);
                    bookingProcess.seatsReservedTimer(booking);
                    Ticket ticket = new Ticket("Ticket"+creationDateTime, seat, reservation.getShowEventInfo(), "reserved");}}
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            return bookingRepository.save(booking);
        }
    }

    public void seatsReservedTimer(Booking reservedBooking){
        Timer reservedTimer = new Timer();
        TimerTask deselectSeatsTimerTask = new TimerTask(){
            public void run(){
                ShowEvent deselectedSeatingPlan = seatingPlan.deselectSeats(reservedBooking.getTicketInfo(), showEventRepository.findByShowEventID(reservedBooking.getShowEventInfo()).get());
                Set<String> clearSeats = new HashSet<>();
                reservedBooking.setTicketInfo(clearSeats);
                reservedBooking.setBookingStatus("no seats selected");
                reservedBooking.setTotalPrice(0);
                reservedBooking.setShowEventInfo(deselectedSeatingPlan.getShowEventID());
                bookingRepository.save(reservedBooking);
                if(reservedBooking.getBookingStatus()=="paid"){ reservedTimer.cancel();}
                }
        };
        reservedTimer.schedule(deselectSeatsTimerTask, 900000);
    }

    public Booking bookSeats(Booking paidBooking){
        customerRepository.save(paidBooking.getCustomerInfo());
        paidBooking.setBookingStatus("paid");
        return bookingRepository.save(paidBooking);
    }
}
