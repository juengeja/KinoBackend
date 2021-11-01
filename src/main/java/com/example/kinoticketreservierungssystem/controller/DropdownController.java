package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import com.example.kinoticketreservierungssystem.repository.CinemaRepository;
import com.example.kinoticketreservierungssystem.repository.EventRoomRepository;
import com.example.kinoticketreservierungssystem.repository.SeatingTemplateRepository;
import com.example.kinoticketreservierungssystem.service.DropdownSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/seatingtemplates")
@RestController
@CrossOrigin(origins = "*")
public class DropdownController {

    @Autowired
    SeatingTemplateRepository seatingTemplateRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    EventRoomRepository eventRoomRepository;
    @Autowired
    DropdownSelection dropdownSelection;

    @GetMapping("/{cinema}")
    public ResponseEntity<Iterable<SeatingTemplate>> frontShowAllSeatingTemplates(@PathVariable String cinema){
        return new ResponseEntity<>(dropdownSelection.getAllSeatingTemplates(cinema), HttpStatus.OK);
    }
}
