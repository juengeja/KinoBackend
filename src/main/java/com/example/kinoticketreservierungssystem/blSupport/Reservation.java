package com.example.kinoticketreservierungssystem.blSupport;

import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

import java.util.Set;

public class Reservation {
    @Id
    private String reservationID;
    private Set<String> seats;
    @PartitionKey
    private String showEventInfo;
    private boolean quickCheckout;
    private String bookingInfo;
    private double totalAmount;

    public Reservation(String reservationID, Set<String> seats, String showEventInfo, String bookingInfo, double totalAmount) {
        this.reservationID = reservationID;
        this.seats = seats;
        this.showEventInfo = showEventInfo;
        this.bookingInfo = bookingInfo;
        this.totalAmount = totalAmount;
    }

    public Reservation() {

    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public Set<String> getSeats() {
        return seats;
    }

    public void setSeats(Set<String> seats) {
        this.seats = seats;
    }

    public String getShowEventInfo() {
        return showEventInfo;
    }

    public void setShowEventInfo(String showEventInfo) {
        this.showEventInfo = showEventInfo;
    }

    public String getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isQuickCheckout() {
        return quickCheckout;
    }

    public void setQuickCheckout(boolean quickCheckout) {
        this.quickCheckout = quickCheckout;
    }
}
