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

    private static ShowEventRepository showEventRepository;
    @Autowired
    public void setDependencyA(ShowEventRepository showEventRepository) {
        this.showEventRepository = showEventRepository;
    }

    private static BookingRepository bookingRepository;
    @Autowired
    public void setDependencyB(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }



    private static Semaphore semaphore;

    public BookingProcess(){
        semaphore = new Semaphore(1);
    }

    public static Booking reserveSeats(List<Seat> seats, ShowEvent showEvent) {
        Booking reserveBooking = new Booking(seats, showEvent);
        try {
            semaphore.acquire();
            SeatingPlan.selectSeats(seats,showEvent);
            reserveBooking.setSeatInfo(seats);
            SeatingPlan.saveSeatingPlan(showEvent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            BookingProcess.seatsReservedTimer(reserveBooking);
            return reserveBooking;
        }
    }



    public static void seatsReservedTimer(Booking reservedBooking){
        Timer reservedTimer = new Timer();
        TimerTask deselectSeatsTimerTask = new TimerTask(){
            public void run(){
                SeatingPlan.deselectSeats(reservedBooking.getSeatInfo(), reservedBooking.getShowEventInfo());
                if(reservedBooking.isPaid()==true){ reservedTimer.cancel();}
            }
        };
        reservedTimer.schedule(deselectSeatsTimerTask, 900000);
    }



    public static Booking bookSeats(Booking paidBooking){
        paidBooking.setPaid(true);
        return paidBooking;
    }


    public static void saveBooking(Booking savedBooking){
        bookingRepository.save(savedBooking);
    }

}
