package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.SeatsSelected;
import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

@Service
public class SeatingPlan {

    private static ShowEventRepository showEventRepository;
    @Autowired
    public void setDependencyA(ShowEventRepository showEventRepository) {
        this.showEventRepository = showEventRepository;
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


    public static Map<Seat,SeatMod> getSeatingPlan(ShowEvent showEvent){
        Mono<ShowEvent> savedShowEvent = showEventRepository.findBy(showEvent);
        Mono<SeatingTemplate> seatingTemplateMono = savedShowEvent.map(ShowEvent::getSeatingTemplateInfo);
        return (Map<Seat, SeatMod>) seatingTemplateMono.map(SeatingTemplate::getSeatMap);
    }


    public static Map<Seat,SeatMod> saveSeatingPlan(ShowEvent showEvent){
        Mono<ShowEvent> savedShowEvent = showEventRepository.save(showEvent);
        Mono<SeatingTemplate> seatingTemplateMono = savedShowEvent.map(ShowEvent::getSeatingTemplateInfo);
        return (Map<Seat,SeatMod>) seatingTemplateMono.map(SeatingTemplate::getSeatMap);
    }


}
