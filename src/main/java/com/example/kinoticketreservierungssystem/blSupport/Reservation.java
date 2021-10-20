package com.example.kinoticketreservierungssystem.blSupport;

import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import org.springframework.data.annotation.Id;

import java.util.Set;

public class Reservation {
    private String reservationID;
    private Set<String> seats;
    private String showEventInfo;

    public Reservation(String reservationID, Set<String> seats, String showEventInfo) {
        this.reservationID = reservationID;
        this.seats = seats;
        this.showEventInfo = showEventInfo;
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
}
