package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Movie;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.repository.MovieRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowEventMovies {

    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllShowEventMovies(){
        List<ShowEvent> showEvents = new ArrayList<>();
        showEventRepository.findAllByLive(true).forEach(showEvents::add);
        List<Movie> movieList = new ArrayList<>();
        for (ShowEvent showEvent:showEvents){
            if(showEvent.getEventStart().isAfter(LocalDateTime.now(ZoneId.of("Europe/Berlin")))){
                movieList.add(showEvent.getMovieInfo());
            }else {
                showEvent.setLive(false);
                showEventRepository.save(showEvent);
            }
        }
        return movieList;
    }

    public List<ShowEvent> getAllShowEventDates(String movieID){
        List<ShowEvent> showEventSchedule = new ArrayList<>();
        Movie movie = movieRepository.findByMovieId(movieID).get();
        showEventRepository.findAllByMovieInfoAndLive(movie,true).forEach(showEventSchedule::add);
        return showEventSchedule;
    }
}
