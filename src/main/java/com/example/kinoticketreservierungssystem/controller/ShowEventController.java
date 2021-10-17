package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import com.example.kinoticketreservierungssystem.service.SeatingPlan;
import com.example.kinoticketreservierungssystem.service.ShowEventMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/moviedata")
@RestController
@CrossOrigin(origins = "*")
public class ShowEventController {

    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    ShowEventMovies showEventMovies;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public List<Movie> frontShowAllMovies(){
        return showEventMovies.getAllShowEventMovies();
    }

    @GetMapping("/moviedates")
    public List<ShowEvent> frontShowEventDates(@PathVariable String movie){
        return showEventMovies.getAllShowEventDates(movie);
    }



  //  @GetMapping
    //public ShowEvent frontShowEvent(@PathVariable String showEventID){
      //  return showEventRepository.findByShowEventID(showEventID).get();
   // }

//    @GetMapping(value = "/seatreservation")
  //  public Map<Seat, SeatMod> frontSeatingPlan(@RequestBody ShowEvent showEvent){
    //    return SeatingPlan.saveSeatingPlan(showEvent);
    //}
}
