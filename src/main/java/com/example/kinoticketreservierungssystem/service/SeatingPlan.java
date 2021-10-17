package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.EventRoom;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.SeatingTemplateRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatingPlan {

    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    SeatingTemplateRepository seatingTemplateRepository;
    @Autowired
    SeatingPlan seatingPlan;


    public ShowEvent selectSeats(List<String> seats, ShowEvent showEvent){
        Map<String, SeatMod> selectSeatingPlanMap = showEvent.getSeatingTemplateInfo().getSeatMap();
        for(String seat : seats){
            selectSeatingPlanMap.get(seat).setBooked(true);
            showEvent.getSeatingTemplateInfo().setSeatMap(selectSeatingPlanMap);}
            return showEventRepository.save(showEvent);
    }

    public ShowEvent deselectSeats(List<String> seats, ShowEvent showEvent){
        Map<String, SeatMod> deselectSeatingPlanMap = showEvent.getSeatingTemplateInfo().getSeatMap();
        for(String seat : seats){
            deselectSeatingPlanMap.get(seat).setBooked(false);
            showEvent.getSeatingTemplateInfo().setSeatMap(deselectSeatingPlanMap);}
            return showEventRepository.save(showEvent);
    }


    public void createSeatingTemplate(EventRoom eventRoom, List<Seat> seats, SeatMod seatMod){
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        String eventRoomID = eventRoom.getEventRoomID();
        Map<String, SeatMod> seatMap = new HashMap<>();
        for(Seat seat : seats){
            seatMap.put(seat.getSeatID(),seatMod);
        };
        seatingTemplateRepository.save(new SeatingTemplate((eventRoomID+"Template"+creationDateTime), eventRoomID, seatMap));
    }

    public Map<String,SeatMod> saveSeatingPlan(ShowEvent showEvent){
        ShowEvent savedShowEvent = showEventRepository.save(showEvent);
        SeatingTemplate seatingTemplate = savedShowEvent.getSeatingTemplateInfo();
        return seatingTemplate.getSeatMap();
    }


}
