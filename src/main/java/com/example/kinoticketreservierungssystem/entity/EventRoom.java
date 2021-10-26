package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;


@Container(containerName = "EventRooms")
public class EventRoom {
    @Id
    private String eventRoomID;
    //screenSizes: small, medium, large
    @PartitionKey
    private String screenSize;
    private Cinema cinemaInfo;

    public EventRoom(String eventRoomID, String screenSize, Cinema cinemaInfo) {
        this.eventRoomID = eventRoomID;
        this.screenSize = screenSize;
        this.cinemaInfo = cinemaInfo;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, EventRoom.class);
    }

    public String getEventRoomID() {
        return eventRoomID;
    }

    public void setEventRoomID(String eventRoomID) {
        this.eventRoomID = eventRoomID;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public Cinema getCinemaInfo() {
        return cinemaInfo;
    }

    public void setCinemaInfo(Cinema cinemaInfo) {
        this.cinemaInfo = cinemaInfo;
    }
}
