package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Booking reserveSeats(String bookingID, List<Seat> seats, ShowEvent showEvent) {
        Booking reserveBooking = new Booking(bookingID, seats, showEvent.getShowEventID());
        try {
            semaphore.acquire();
            reserveBooking.setSeatInfo(seats);
            reserveBooking.setShowEventInfo(seatingPlan.selectSeats(seats,showEvent).getShowEventID());

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
                List<Seat> clearSeats = new ArrayList<>();
                reservedBooking.setSeatInfo(clearSeats);
                reservedBooking.setShowEventInfo(deselectedSeatingPlan.getShowEventID());
                bookingRepository.save(reservedBooking);
                if(reservedBooking.isPaid()==true){ reservedTimer.cancel();}
                }
        };
        reservedTimer.schedule(deselectSeatsTimerTask, 900000);
    }



    public Booking bookSeats(Booking paidBooking){
        paidBooking.setPaid(true);
        return paidBooking;
    }


    public void saveBooking(Booking savedBooking){
        bookingRepository.save(savedBooking);
    }

}
