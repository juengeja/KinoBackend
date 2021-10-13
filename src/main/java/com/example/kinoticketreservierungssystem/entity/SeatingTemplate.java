package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Container(containerName = "SeatingTemplates", ru = "400")
public class SeatingTemplate {
    @Id
    private String seatingTemplateID;
    @PartitionKey
    private EventRoom eventRoomInfo;
    private Map<Seat, SeatMod> seatMap;

    public SeatingTemplate(String seatingTemplateID, EventRoom eventRoomInfo, Map<Seat, SeatMod> seatMap) {
        this.seatingTemplateID = seatingTemplateID;
        this.eventRoomInfo = eventRoomInfo;
        this.seatMap = seatMap;
    }

    public String getSeatingTemplateID() {
        return seatingTemplateID;
    }

    public void setSeatingTemplateID(String seatingTemplateID) {
        this.seatingTemplateID = seatingTemplateID;
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
