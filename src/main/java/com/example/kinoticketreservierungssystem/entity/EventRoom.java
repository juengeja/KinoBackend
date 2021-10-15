package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "EventRooms", ru = "400")
public class EventRoom {
    @Id
    private String eventRoomID;
    //screenSizes: small, medium, big
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
        return "EventRoom{" +
                "eventRoomID='" + eventRoomID + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", cinemaInfo=" + cinemaInfo +
                '}';
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
