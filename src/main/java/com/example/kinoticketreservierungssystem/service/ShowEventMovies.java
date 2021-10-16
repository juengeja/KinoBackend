package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowEventMovies {

    @Autowired
    ShowEventRepository showEventRepository;




    public List<ShowEvent> getAllShowEventData() {
        List<ShowEvent> showEventSchedule = new ArrayList<>();
        showEventRepository.queryShowEventSchedule().forEach(showEventSchedule::add);
        return showEventSchedule;
    }

    //LocalDateTime currentTime;
   // List<ShowEvent> showEvents = new ArrayList<ShowEvent>();
    //for(ShowEvent showEvent : showEvents){
      //  int duration = showEvent.getDuration();
        //LocalDateTime eventStart = showEvent.getEventStart();
}
