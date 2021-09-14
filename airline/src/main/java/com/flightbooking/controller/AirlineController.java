package com.flightbooking.controller;

import com.flightbooking.data.Airline;
import com.flightbooking.dtos.AirlineDto;
import com.flightbooking.service.AirlineService;
import org.codehaus.commons.compiler.util.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/airline/inventory/")
@RestController

public class AirlineController {

    @Autowired
    private AirlineService airlineService;


    @PostMapping ("/add")
    public AirlineDto addAirline(@RequestBody AirlineDto airlineDto){

        return airlineService.addAirline(airlineDto);

    }

    @PutMapping("/modify/{id}")
    public AirlineDto modifyAirline(@PathVariable int id,@RequestBody AirlineDto airlineDto){
        return airlineService.modifyAirline(id, airlineDto);
    }

    @GetMapping("/getall")
    public List<Airline> getAllAirlines(){

        return airlineService.getAllAirlines();
    }

    @DeleteMapping("/delete/{flightNumber}")
    public String deleteAirlineByFlightNumber(@PathVariable String flightNumber){
            return deleteAirlineByFlightNumber(flightNumber);
    }


}

