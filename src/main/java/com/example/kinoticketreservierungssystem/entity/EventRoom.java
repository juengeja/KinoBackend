package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "EventRooms", ru = "400")
public class EventRoom {
    @Id
    private String eventRoomID;
    @PartitionKey
    private Cinema cinemaInfo = new Cinema();

    public EventRoom(String eventRoomID, Cinema cinemaInfo) {
        this.eventRoomID = eventRoomID;
        this.cinemaInfo = cinemaInfo;
    }

    public EventRoom() {

    }

    public String getEventRoomID() {
        return eventRoomID;
    }

    public void setEventRoomID(String eventRoomID) {
        this.eventRoomID = eventRoomID;
    }

    public Cinema getCinemaInfo() {
        return cinemaInfo;
    }

    public void setCinemaInfo(Cinema cinemaInfo) {
        this.cinemaInfo = cinemaInfo;
    }
}
