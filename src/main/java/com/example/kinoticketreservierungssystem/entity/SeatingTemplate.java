package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.TreeMap;

@Container(containerName = "SeatingTemplates", ru = "400")
public class SeatingTemplate {
    @Id
    private String seatingTemplateID;
    @PartitionKey
    private EventRoom eventRoomInfo;
    private Map<Seat,Float> seatPricingMap;

    public SeatingTemplate(String seatingTemplateID, EventRoom eventRoomInfo, Map<Seat, Float> seatPricingMap) {
        this.seatingTemplateID = seatingTemplateID;
        this.eventRoomInfo = eventRoomInfo;
        this.seatPricingMap = seatPricingMap;
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

    public Map<Seat, Float> getSeatPricingMap() {
        return seatPricingMap;
    }

    public void setSeatPricingMap(Map<Seat, Float> seatPricingMap) {
        this.seatPricingMap = seatPricingMap;
    }
}
