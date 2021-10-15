package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.example.kinoticketreservierungssystem.blSupport.ObjectUtils;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Container(containerName = "SeatingTemplates", ru = "400")
public class SeatingTemplate {
    // seatingTemplateID naming convention: eventroomID+Template+(localdatetime now .toString)
    @Id
    private String seatingTemplateID;
    @PartitionKey
    private boolean bookedOut = false;
    @JsonIgnore
    private Map<Seat, SeatMod> seatMap;

    public SeatingTemplate(String seatingTemplateID, boolean bookedOut, Map<Seat, SeatMod> seatMap) {
        this.seatingTemplateID = seatingTemplateID;
        this.bookedOut = bookedOut;
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


    @JsonProperty("seatMap")
    public Map<Seat, SeatMod> getSeatMap() {
        return ObjectUtils.toList(seatMap);
    }
    @JsonProperty("seatMap")
    public void setSeatMap(Map<Seat, SeatMod> seatMap) {
        this.seatMap = seatMap;
    }
}
