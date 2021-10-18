package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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


    private static Semaphore semaphore;

    public BookingProcess(){
        semaphore = new Semaphore(1);
    }

    public Booking reserveSeats(List<String> seats, ShowEvent showEvent) {
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        int totalAmount = 0;
        Booking reserveBooking = new Booking("booking"+creationDateTime, seats, showEvent.getShowEventID());
        try {
            semaphore.acquire();
            reserveBooking.setSeatInfo(seats);
            for(String seat:seats){
                totalAmount += showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).getPrice();
            }
            reserveBooking.setTotalPrice(totalAmount);
            ShowEvent reservedShowEvent = seatingPlan.selectSeats(seats,showEvent);
            reserveBooking.setShowEventInfo(reservedShowEvent.getShowEventID());
            reserveBooking.setReservedSeatMap(reservedShowEvent.getSeatingTemplateInfo().getSeatMap());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            bookingProcess.seatsReservedTimer(reserveBooking);
            return bookingRepository.save(reserveBooking);
        }
    }

    public void seatsReservedTimer(Booking reservedBooking){
        Timer reservedTimer = new Timer();
        TimerTask deselectSeatsTimerTask = new TimerTask(){
            public void run(){
                ShowEvent deselectedSeatingPlan = seatingPlan.deselectSeats(reservedBooking.getSeatInfo(), showEventRepository.findByShowEventID(reservedBooking.getShowEventInfo()).get());
                List<String> clearSeats = new ArrayList<>();
                reservedBooking.setSeatInfo(clearSeats);
                reservedBooking.setShowEventInfo(deselectedSeatingPlan.getShowEventID());
                bookingRepository.save(reservedBooking);
                if(reservedBooking.isPaid()==true){ reservedTimer.cancel();}
                }
        };
        reservedTimer.schedule(deselectSeatsTimerTask, 900000);
    }

    public Booking bookSeats(Booking paidBooking, Customer customer){
        paidBooking.setCustomerInfo(customer);
        paidBooking.setReservedSeatMap(null);
        paidBooking.setPaid(true);
        return bookingRepository.save(paidBooking);
    }
}
