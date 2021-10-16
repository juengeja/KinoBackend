package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Container(containerName = "SeatingTemplates", ru = "400")
public class SeatingTemplate {
    // seatingTemplateID naming convention: eventroomID+Template+(localdatetime now .toString)
    @Id
    private String seatingTemplateID;
    @PartitionKey
    private boolean bookedOut = false;
    private Map<String, SeatMod> seatMap;

    public SeatingTemplate(String seatingTemplateID, boolean bookedOut, Map<String, SeatMod> seatMap) {
        this.seatingTemplateID = seatingTemplateID;
        this.bookedOut = bookedOut;
        this.seatMap = seatMap;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, SeatingTemplate.class);
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

    public Map<String, SeatMod> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(Map<String, SeatMod> seatMap) {
        this.seatMap = seatMap;
    }
}
