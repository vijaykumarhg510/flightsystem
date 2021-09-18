package com.flightbooking.controller;

import com.flightbooking.dtos.SeatsDto;
import com.flightbooking.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/seats")
@RestController
public class SeatsController {
    @Autowired
    private SeatsService seatsService;

    @PostMapping("/add/{flightNumber}")
    public SeatsDto addSeats(@PathVariable String flightNumber, @RequestBody SeatsDto seatsDto){
        return seatsService.addSeats(flightNumber,seatsDto);
    }

    @PutMapping("/edit/{flightNumber}")
    public SeatsDto modifySeats(@PathVariable String flightNumber,@RequestBody SeatsDto seatsDto){
        return seatsService.modifySeats(flightNumber,seatsDto);
    }

    @GetMapping("/getAll/{flightNumber}")
    public SeatsDto getSeatsByFlightNumber(@PathVariable String flightNumber){
        return seatsService.getSeatsByFlightNumber(flightNumber);
    }

    @DeleteMapping("/delete/{flightNumber}")
    public SeatsDto deleteSeatsByFlightNumber(@PathVariable String flightNumber){
        return seatsService.deleteSeatsByFlightNumber(flightNumber);
    }
}
