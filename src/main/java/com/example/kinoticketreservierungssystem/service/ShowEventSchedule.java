package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowEventSchedule {


    private static ShowEventRepository showEventRepository;
    @Autowired
    public void setDependencyA(ShowEventRepository showEventRepository) {
        this.showEventRepository = showEventRepository;
    }


    public static List<ShowEvent> getAllShowEventData() {
        Flux<ShowEvent> showEventFlux = showEventRepository.queryShowEventSchedule();
        List<ShowEvent> showEventSchedule = showEventFlux.collectList().block();
        return showEventSchedule;
    }

    //LocalDateTime currentTime;
   // List<ShowEvent> showEvents = new ArrayList<ShowEvent>();
    //for(ShowEvent showEvent : showEvents){
      //  int duration = showEvent.getDuration();
        //LocalDateTime eventStart = showEvent.getEventStart();
}
