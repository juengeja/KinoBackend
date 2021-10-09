package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Container(containerName = "ShowEvents", ru = "400")
public class ShowEvent {
    @Id
    private String showEventID;
    private EventRoom eventRoomInfo = new EventRoom();
    private Movie movieInfo = new Movie();
    private LocalDateTime eventStart;
    private int duration;
    @PartitionKey
    private boolean is3D;
    private boolean hasFreeSeats;
    private Map<Seat,Float> seatPricingMap;

    public ShowEvent(String showEventID, EventRoom eventRoomInfo, Movie movieInfo, LocalDateTime eventStart, int duration, boolean is3D, boolean hasFreeSeats, Map<Seat, Float> seatPricingMap) {
        this.showEventID = showEventID;
        this.eventRoomInfo = eventRoomInfo;
        this.movieInfo = movieInfo;
        this.eventStart = eventStart;
        this.duration = duration;
        this.is3D = is3D;
        this.hasFreeSeats = hasFreeSeats;
        this.seatPricingMap = seatPricingMap;
    }

    public ShowEvent() {

    }

    public String getShowEventID() {
        return showEventID;
    }

    public void setShowEventID(String showEventID) {
        this.showEventID = showEventID;
    }

    public EventRoom getEventRoomInfo() {
        return eventRoomInfo;
    }

    public void setEventRoomInfo(EventRoom eventRoomInfo) {
        this.eventRoomInfo = eventRoomInfo;
    }

    public Movie getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(Movie movieInfo) {
        this.movieInfo = movieInfo;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isHasFreeSeats() {
        return hasFreeSeats;
    }

    public void setHasFreeSeats(boolean hasFreeSeats) {
        this.hasFreeSeats = hasFreeSeats;
    }

    public Map<Seat, Float> getSeatPricingMap() {
        return seatPricingMap;
    }

    public void setSeatPricingMap(Map<Seat, Float> seatPricingMap) {
        this.seatPricingMap = seatPricingMap;
    }
}
