package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Container(containerName = "SeatingTemplates", ru = "400")
public class SeatingTemplate {
    // seatingTemplateID naming convention: eventroomID+Template+(localdatetime now .toString)
    @Id
    private String seatingTemplateID;
    @PartitionKey
    private boolean bookedOut = false;
    private EventRoom eventRoomInfo;
    private Map<Seat, SeatMod> seatMap;

    public SeatingTemplate(String seatingTemplateID, boolean bookedOut, EventRoom eventRoomInfo, Map<Seat, SeatMod> seatMap) {
        this.seatingTemplateID = seatingTemplateID;
        this.bookedOut = bookedOut;
        this.eventRoomInfo = eventRoomInfo;
        this.seatMap = seatMap;
    }

    public String getSeatingTemplateID() {
        return seatingTemplateID;
    }

    public void setSeatingTemplateID(String seatingTemplateID) {
        this.seatingTemplateID = seatingTemplateID;
    }

    public boolean isBookedOut() {
        return bookedOut;
    }

    public void setBookedOut(boolean bookedOut) {
        this.bookedOut = bookedOut;
    }

    public EventRoom getEventRoomInfo() {
        return eventRoomInfo;
    }

    public void setEventRoomInfo(EventRoom eventRoomInfo) {
        this.eventRoomInfo = eventRoomInfo;
    }

    public Map<Seat, SeatMod> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(Map<Seat, SeatMod> seatMap) {
        this.seatMap = seatMap;
    }
}
