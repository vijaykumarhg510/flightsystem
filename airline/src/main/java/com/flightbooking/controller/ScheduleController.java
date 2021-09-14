package com.flightbooking.controller;

import com.flightbooking.data.AirlineSchedule;
import com.flightbooking.dtos.AirlineScheduleDto;
import com.flightbooking.dtos.SeatsDto;
import com.flightbooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/airline/schedule")
@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    @PostMapping("/add/{flightNumber}")
    public AirlineScheduleDto addSchedule(@PathVariable String flightNumber, @RequestBody AirlineScheduleDto airlineScheduleDto){
       return scheduleService.addSchedule(flightNumber,airlineScheduleDto);
    }

//    @PostMapping("/addSeats/{airlineScheduleId}")
//    public SeatsDto addSeats(@PathVariable int airlineScheduleId,@RequestBody SeatsDto seatsDto){
//
//        return null;
//    }

    @GetMapping("/getAll/{flightNumber}")
    public List<AirlineScheduleDto> getAllSchedulesByFlightNumber(@PathVariable String flightNumber){
       return scheduleService.getAllSchedulesByFlightNumber(flightNumber);
    }

    @PutMapping("/edit/{id}/{flightNumber}")
    public AirlineScheduleDto modifySchedule(@PathVariable int id,@PathVariable String flightNumber,
                                             @RequestBody AirlineScheduleDto airlineScheduleDto){
        return scheduleService.modifySchedule(id, flightNumber, airlineScheduleDto);
    }

    @DeleteMapping("/delete/{id}/{flightNumber}")
    public String deleteSchedule(@PathVariable int id,@PathVariable String flightNumber){
        return scheduleService.deleteSchedule(id, flightNumber);
    }

}
