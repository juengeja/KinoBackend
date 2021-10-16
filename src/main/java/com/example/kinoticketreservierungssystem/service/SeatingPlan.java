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


    public ShowEvent selectSeats(List<Seat> seats, ShowEvent showEvent){
        Map<String, SeatMod> selectSeatingPlanMap = showEvent.getSeatingTemplateInfo().getSeatMap();
        for(Seat seat : seats){
            SeatMod seatMod = selectSeatingPlanMap.get(seat);
            seatMod.setBooked(true);
            showEvent.getSeatingTemplateInfo().setSeatMap(selectSeatingPlanMap);}
            return showEvent;
    }

    public ShowEvent deselectSeats(List<Seat> seats, ShowEvent showEvent){
        Map<String, SeatMod> deselectSeatingPlanMap = seatingPlan.getSeatingPlan(showEvent);
        for(Seat seat : seats){
            SeatMod seatMod = deselectSeatingPlanMap.get(seat);
            seatMod.setBooked(false);
            showEvent.getSeatingTemplateInfo().setSeatMap(deselectSeatingPlanMap);}
            return showEvent;
    }


    public void createSeatingTemplate(EventRoom eventRoom, List<Seat> seats, SeatMod seatMod){
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        String eventRoomID = eventRoom.getEventRoomID();
        Map<String, SeatMod> seatMap = new HashMap<>();
        for(Seat seat : seats){
            seatMap.put(seat.getSeatID(),seatMod);
        };
        seatingTemplateRepository.save(new SeatingTemplate((eventRoomID+"Template"+creationDateTime), false, seatMap));
    }

    public Map<String,SeatMod> getSeatingPlan(ShowEvent showEvent){
        ShowEvent savedShowEvent = showEventRepository.findBy(showEvent).get();
        SeatingTemplate seatingTemplate = savedShowEvent.getSeatingTemplateInfo();
        return seatingTemplate.getSeatMap();
    }


    public Map<String,SeatMod> saveSeatingPlan(ShowEvent showEvent){
        ShowEvent savedShowEvent = showEventRepository.save(showEvent);
        SeatingTemplate seatingTemplate = savedShowEvent.getSeatingTemplateInfo();
        return seatingTemplate.getSeatMap();
    }


}
