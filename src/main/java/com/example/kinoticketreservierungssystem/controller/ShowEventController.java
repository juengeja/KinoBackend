package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import com.example.kinoticketreservierungssystem.service.SeatingPlan;
import com.example.kinoticketreservierungssystem.service.ShowEventSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/moviedata")
@RestController
@CrossOrigin(origins = "*")
public class ShowEventController {

    private static ShowEventRepository showEventRepository;

    @Autowired
    public void setDependencyA(ShowEventRepository showEventRepository) {
        this.showEventRepository = showEventRepository;
    }


    @GetMapping
    public List<ShowEvent> frontShowAllMovies(){
        return ShowEventSchedule.getAllShowEventData();
    }

    private static List<ShowEvent> showEvents;

    @GetMapping
    public Mono<ShowEvent> frontShowEvent(@PathVariable String showEventID){
        return showEventRepository.findByShowEventID(showEventID);
    }

    @GetMapping(value = "/seatreservation")
    public Map<Seat,Double> frontSeatingPlan(@RequestBody ShowEvent showEvent){
        return SeatingPlan.saveSeatingPlan(showEvent);
    }
}
