package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.BookingRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Booking reserveSeats(List<Seat> seats, ShowEvent showEvent) {
        Booking reserveBooking = new Booking(seats, showEvent);
        try {
            semaphore.acquire();
            seatingPlan.selectSeats(seats,showEvent);
            reserveBooking.setSeatInfo(seats);
            seatingPlan.saveSeatingPlan(showEvent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            bookingProcess.seatsReservedTimer(reserveBooking);
            return reserveBooking;
        }
    }



    public void seatsReservedTimer(Booking reservedBooking){
        Timer reservedTimer = new Timer();
        TimerTask deselectSeatsTimerTask = new TimerTask(){
            public void run(){
                seatingPlan.deselectSeats(reservedBooking.getSeatInfo(), reservedBooking.getShowEventInfo());
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
