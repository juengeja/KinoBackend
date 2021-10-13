package com.example.kinoticketreservierungssystem.blSupport;

import com.example.kinoticketreservierungssystem.entity.Seat;

import java.util.List;
import java.util.Map;

public class SeatsSelected {
    private Map<Seat,SeatMod> seatingPlanSelectedMap;
    private List<Seat> seatsSelectedList;
    private String showEventID;
    private boolean booked = false;

    public SeatsSelected(Map<Seat, SeatMod> seatingPlanSelectedMap, List<Seat> seatsSelectedList, String showEventID, boolean booked) {
        this.seatingPlanSelectedMap = seatingPlanSelectedMap;
        this.seatsSelectedList = seatsSelectedList;
        this.showEventID = showEventID;
        this.booked = booked;
    }

    public SeatsSelected() {

    }

    public Map<Seat, SeatMod> getSeatingPlanSelectedMap() {
        return seatingPlanSelectedMap;
    }

    public void setSeatingPlanSelectedMap(Map<Seat, SeatMod> seatingPlanSelectedMap) {
        this.seatingPlanSelectedMap = seatingPlanSelectedMap;
    }

    public List<Seat> getSeatsSelectedList() {
        return seatsSelectedList;
    }

    public void setSeatsSelectedList(List<Seat> seatsSelectedList) {
        this.seatsSelectedList = seatsSelectedList;
    }

    public String getShowEventID() {
        return showEventID;
    }

    public void setShowEventID(String showEventID) {
        this.showEventID = showEventID;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
