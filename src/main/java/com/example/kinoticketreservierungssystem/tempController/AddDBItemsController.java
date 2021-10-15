package com.example.kinoticketreservierungssystem.tempController;

import com.example.kinoticketreservierungssystem.blSupport.SeatMod;
import com.example.kinoticketreservierungssystem.entity.Cinema;
import com.example.kinoticketreservierungssystem.entity.EventRoom;
import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.service.AddSeats;
import com.example.kinoticketreservierungssystem.service.CreateEntities;
import com.example.kinoticketreservierungssystem.service.SeatingPlan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping(value = "/dbitems")
@RestController
@CrossOrigin(origins = "*")
public class AddDBItemsController {



    @PostMapping("createcinema")
    public void backCreateCinema(){
        CreateEntities.createCinema("IndigoBW","germany","hesse","heidelberg","albrechtstraße",12,34567,"indigobw@gmail.com","012345678901");
    }

    @PostMapping("/createroom")
    public void backCreateRoom(){
        Cinema cinema = CreateEntities.getCinema("IndigoBW");
        CreateEntities.createEventRoom("Delta", "small", cinema);
    }

    @PostMapping("/createseats")
    public void backCreateSeats(){
        EventRoom eventRoom = CreateEntities.getEventRoom("Astra");
        List<Seat> seats = AddSeats.addSeats(eventRoom,10,15);
        AddSeats.createSeats(seats);
    }
    @PostMapping("/createseatingtemplate")
    public void backCreateSeatingTemplate(){
        SeatingPlan.createSeatingTemplate(CreateEntities.getEventRoom("Astra"), CreateEntities.getSeatsPerRoom("Astra"), new SeatMod(8.00,false));
    }
}
