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
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SeatingPlan {

    private static ShowEventRepository showEventRepository;
    @Autowired
    public void setDependencyA(ShowEventRepository showEventRepository) {
        this.showEventRepository = showEventRepository;
    }

    private static SeatingTemplateRepository seatingTemplateRepository;
    @Autowired
    public void setDependencyB(SeatingTemplateRepository seatingTemplateRepository) {
        this.seatingTemplateRepository = seatingTemplateRepository;
    }


    public static ShowEvent selectSeats(List<Seat> seats, ShowEvent showEvent){
        Map<Seat, SeatMod> selectSeatingPlanMap = showEvent.getSeatingTemplateInfo().getSeatMap();
        for(Seat seat : seats){
            SeatMod seatMod = selectSeatingPlanMap.get(seat);
            seatMod.setBooked(true);
            showEvent.getSeatingTemplateInfo().setSeatMap(selectSeatingPlanMap);}
            return showEvent;
    }

    public static ShowEvent deselectSeats(List<Seat> seats, ShowEvent showEvent){
        Map<Seat, SeatMod> deselectSeatingPlanMap = SeatingPlan.getSeatingPlan(showEvent);
        for(Seat seat : seats){
            SeatMod seatMod = deselectSeatingPlanMap.get(seat);
            seatMod.setBooked(false);
            showEvent.getSeatingTemplateInfo().setSeatMap(deselectSeatingPlanMap);}
            return showEvent;
    }


    public static SeatingTemplate createSeatingTemplate(EventRoom eventRoom, List<Seat> seats, SeatMod seatMod){
        String creationDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin")).toString();
        String eventRoomID = eventRoom.getEventRoomID();
        Map<Seat,SeatMod> seatMap = new HashMap<>();
        for(Seat seat : seats){
            seatMap.put(seat,seatMod);
        }
        return seatingTemplateRepository.save(new SeatingTemplate((eventRoomID+"Template"+creationDateTime), false, eventRoom, seatMap)).block();
    }

    public static Map<Seat,SeatMod> getSeatingPlan(ShowEvent showEvent){
        Mono<ShowEvent> savedShowEvent = showEventRepository.findBy(showEvent);
        Mono<SeatingTemplate> seatingTemplateMono = savedShowEvent.map(ShowEvent::getSeatingTemplateInfo);
        return seatingTemplateMono.map(SeatingTemplate::getSeatMap).block();
    }


    public static Map<Seat,SeatMod> saveSeatingPlan(ShowEvent showEvent){
        Mono<ShowEvent> savedShowEvent = showEventRepository.save(showEvent);
        Mono<SeatingTemplate> seatingTemplateMono = savedShowEvent.map(ShowEvent::getSeatingTemplateInfo);
        return seatingTemplateMono.map(SeatingTemplate::getSeatMap).block();
    }


}
