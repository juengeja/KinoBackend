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
        }
        Booking booking = bookingRepository.findByBookingID(reservation.getBookingInfo()).get();
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
                    if(reservations!=null){
                    reservations.remove(reservation);
                    booking.setReservations(reservations);}
                    seatingPlan.deselectSeats(seatsAdded, showEvent);
                    booking.setBookingStatus("denied");
                    break;
            }else{seatsAdded.add(seat);
                    reservation.setTotalAmount(reservation.getTotalAmount()+showEvent.getSeatingTemplateInfo().getSeatMap().get(seat).getPrice());
                    Ticket ticket = new Ticket("Ticket"+seat, seat, reservation.getShowEventInfo(), "reserved");
                    ticketsAdded.add(ticket);
                    ticketRepository.save(ticket);
                    Set<String> tickets = booking.getTickets();
                    if(tickets==null){
                        tickets = new HashSet<>();
                    }
                    tickets.add(ticket.getTicketID());

                    booking.setTickets(tickets);
                    Set<Reservation> reservations = booking.getReservations();
                    if(reservations==null){
                        reservations = new HashSet<>();
                    }
                    reservation.setMovieName(showEvent.getMovieInfo().getMovieName());
                    reservation.setMoviePoster(showEvent.getMovieInfo().getImg());
                    reservation.setEventStart(showEvent.getEventStart());
                    reservations.add(reservation);
                    booking.setReservations(reservations);
                    seatingPlan.selectSeats(seatsAdded, showEvent);
                    bookingProcess.seatsReservedTimer(reservation);
                    booking.setBookingStatus("reserved");
                }
                booking.setTotalPrice(booking.getTotalPrice()+reservation.getTotalAmount());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reservationRepository.save(reservation);
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
                booking.setTotalPrice(booking.getTotalPrice()-reservation.getTotalAmount());
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
