package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
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


    private static Semaphore semaphore;

    public BookingProcess(){
        semaphore = new Semaphore(1);
    }

    public Booking reserveSeats(Booking reserveBooking) {
        ShowEvent showEvent = showEventRepository.findByShowEventID(reserveBooking.getShowEventInfo()).get();
        Set<String> seats = reserveBooking.getSeatInfo();
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        int totalAmount = 0;
        try {
            semaphore.acquire();
            Set<String> seatsAdded = new HashSet<>();
            for(String seat:seats){
                if(showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).isBooked()==true){
                    reserveBooking.setBookingStatus("denied");
                        seatingPlan.deselectSeats(seatsAdded, showEvent);
                    break;
            } else{seatsAdded.add(seat);
                    totalAmount += showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).getPrice();
                    ShowEvent reservedShowEvent = seatingPlan.selectSeats(seats,showEvent);
                    reserveBooking.setShowEventInfo(reservedShowEvent.getShowEventID());
                    reserveBooking.setBookingStatus("reserved");
                    reserveBooking.setTotalPrice(totalAmount);}}
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
                Set<String> clearSeats = new HashSet<>();
                reservedBooking.setSeatInfo(clearSeats);
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
        paidBooking.setBookingStatus("paid");
        return bookingRepository.save(paidBooking);
    }
}
