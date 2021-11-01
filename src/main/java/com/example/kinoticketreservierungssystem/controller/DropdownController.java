package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.entity.SeatingTemplate;
import com.example.kinoticketreservierungssystem.repository.SeatingTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/seatingtemplates")
@RestController
@CrossOrigin(origins = "*")
public class DropdownController {

    @Autowired
    SeatingTemplateRepository seatingTemplateRepository;

    @GetMapping
    public ResponseEntity<Iterable<SeatingTemplate>> frontShowAllSeatingTemplates(){
        return new ResponseEntity<>(seatingTemplateRepository.findAll(), HttpStatus.OK);
    }
}
