package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.Reservation;
import com.example.kinoticketreservierungssystem.entity.*;
import com.example.kinoticketreservierungssystem.repository.*;
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
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ReservationRepository reservationRepository;

    private static Semaphore semaphore;

    public BookingProcess(){
        semaphore = new Semaphore(1);
    }

    public Booking reserveSeats(Reservation reservation) {
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        if(reservation.getBookingInfo()==null){
            String bookingID = "Booking"+creationDateTime;
            if(reservation.isQuickCheckout() == true){
            bookingRepository.save(new Booking(bookingID, true));}
            else{bookingRepository.save(new Booking(bookingID,false));}
            reservation.setBookingInfo(bookingID);
            reservationRepository.save(reservation);
        }
        Booking booking = bookingRepository.findById(reservation.getBookingInfo()).get();
        ShowEvent showEvent = showEventRepository.findByShowEventID(reservation.getShowEventInfo()).get();
        Set<String> seats = reservation.getSeats();
        try {
            semaphore.acquire();
            Set<String> seatsAdded = new HashSet<>();
            Set<Ticket> ticketsAdded = new HashSet<>();
            for(String seat:seats){

                if(showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).isBooked()==true){
                    reservation.setTotalAmount(0);
                    Set<Reservation> reservations = booking.getReservations();
                    reservations.remove(reservation);
                    booking.setReservations(reservations);
                    booking.setBookingStatus("denied");
                    seatingPlan.deselectSeats(seatsAdded, showEvent);
                    break;
            }else{seatsAdded.add(seat);
                    reservation.setTotalAmount(+showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).getPrice());
                    seatingPlan.selectSeats(seatsAdded, showEvent);
                    Ticket ticket = new Ticket("Ticket"+seat, seat, reservation.getShowEventInfo(), "reserved");
                    ticketsAdded.add(ticket);
                    Set<String> tickets = booking.getTickets();
                    tickets.add(ticket.getTicketID());
                    booking.setTickets(tickets);
                    bookingProcess.seatsReservedTimer(reservation);
                    Set<Reservation> reservations = booking.getReservations();
                    reservations.add(reservation);
                    booking.setReservations(reservations);
                    booking.setBookingStatus("reserved");
                }
                booking.setTotalPrice(reservation.getTotalAmount());
                for(Ticket ticket: ticketsAdded){
                ticketRepository.save(ticket);}
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            return bookingRepository.save(booking);
        }
    }

    public void seatsReservedTimer(Reservation reservation){
        Timer reservedTimer = new Timer();

        TimerTask deselectSeatsTimerTask = new TimerTask(){
            public void run(){
                Booking booking = bookingRepository.findByBookingID(reservation.getBookingInfo()).get();
                if(booking.getBookingStatus()=="paid"){ reservedTimer.cancel();}
                ShowEvent deselectedSeatingPlan = seatingPlan.deselectSeats(reservation.getSeats(), showEventRepository.findByShowEventID(reservation.getShowEventInfo()).get());
                Set<String> clearSeats = new HashSet<>();
                reservation.setSeats(clearSeats);
                Set <Reservation> removeReservation = booking.getReservations();
                removeReservation.remove(reservation);
                booking.setReservations(removeReservation);
                booking.setTotalPrice(-reservation.getTotalAmount());
                bookingRepository.save(booking);
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
