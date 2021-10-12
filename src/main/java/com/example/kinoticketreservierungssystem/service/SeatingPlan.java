package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Semaphore;

@Service
public class SeatingPlan {

    private static ShowEventRepository showEventRepository;
    @Autowired
    public void setDependencyA(ShowEventRepository showEventRepository) {
        this.showEventRepository = showEventRepository;
    }


    private Semaphore semaphore;

    public SeatingPlan(){
        semaphore = new Semaphore(1);
    }

    public Map<Seat,Double> reserve(Seat seat, String showEventID) {
        Map<Seat, Double> seatingPlanReserve = null;
        try {
            semaphore.acquire();
            seatingPlanReserve = SeatingPlan.getSeatingPlan(showEventID);
            Double price = seatingPlanReserve.get(seat);
            seatingPlanReserve.put(seat, price);
            return seatingPlanReserve;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            return seatingPlanReserve;
        }
    }


    public static Map<Seat,Double> getSeatingPlan(String showEventID){
        Mono<ShowEvent> savedShowEvent = showEventRepository.findById(showEventID);
        Mono<SeatingTemplate> seatingTemplateMono = savedShowEvent.map(ShowEvent::getSeatingTemplateInfo);
        return (Map<Seat, Double>) seatingTemplateMono.map(SeatingTemplate::getSeatPricingMap);
    }

    public static Map<Seat,Double> saveSeatingPlan(ShowEvent showEvent){
    Mono<ShowEvent> savedShowEvent = showEventRepository.save(showEvent);
    Mono<SeatingTemplate> seatingTemplateMono = savedShowEvent.map(ShowEvent::getSeatingTemplateInfo);
    return (Map<Seat,Double>) seatingTemplateMono.map(SeatingTemplate::getSeatPricingMap);
    }


}
