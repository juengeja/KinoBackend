package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

public class Ticket {
    @Id
    private String ticketID;
    @PartitionKey
    private String seatInfo;
    private String showEventInfo;
    //after successful reservation: "reserved", after successful payment: "booked", after validation: "validated"/after expiration: "expired"
    private String ticketStatus;

    public Ticket(String ticketID, String seatInfo, String showEventInfo, String ticketStatus) {
        this.ticketID = ticketID;
        this.seatInfo = seatInfo;
        this.showEventInfo = showEventInfo;
        this.ticketStatus = ticketStatus;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getShowEventInfo() {
        return showEventInfo;
    }

    public void setShowEventInfo(String showEventInfo) {
        this.showEventInfo = showEventInfo;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
