package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "Seats", ru= "400" )
public class Seat {
    // seatID naming convention: EventRoomID + Row + SeatNumber
    @Id
    private String seatID;
    @PartitionKey
    private EventRoom eventRoomInfo;
    // row naming convention: A,B,C,D...
    private char row;
    private int seatNumber;
    private double price;
    private boolean booked;

    public Seat(String seatID, EventRoom eventRoomInfo, char row, int seatNumber, double price, boolean booked) {
        this.seatID = seatID;
        this.eventRoomInfo = eventRoomInfo;
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
        this.booked = booked;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public EventRoom getEventRoomInfo() {
        return eventRoomInfo;
    }

    public void setEventRoomInfo(EventRoom eventRoomInfo) {
        this.eventRoomInfo = eventRoomInfo;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
