package com.flightbooking.controller;

import com.flightbooking.data.AirlineSchedule;
import com.flightbooking.dtos.AirlineScheduleDto;
import com.flightbooking.dtos.SeatsDto;
import com.flightbooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    @PostMapping("/schedule/add/{flightNumber}")
    public AirlineScheduleDto addSchedule(@PathVariable String flightNumber, @RequestBody AirlineScheduleDto airlineScheduleDto){
       return scheduleService.addSchedule(flightNumber,airlineScheduleDto);
    }

//    @PostMapping("/addSeats/{airlineScheduleId}")
//    public SeatsDto addSeats(@PathVariable int airlineScheduleId,@RequestBody SeatsDto seatsDto){
//
//        return null;
//    }

    @GetMapping("/schedule/getAll/{flightNumber}")
    public List<AirlineScheduleDto> getAllSchedulesByFlightNumber(@PathVariable String flightNumber){
       return scheduleService.getAllSchedulesByFlightNumber(flightNumber);
    }

    @PutMapping("/schedule/edit/{id}/{flightNumber}")
    public AirlineScheduleDto modifySchedule(@PathVariable int id,@PathVariable String flightNumber,
                                             @RequestBody AirlineScheduleDto airlineScheduleDto){
        return scheduleService.modifySchedule(id, flightNumber, airlineScheduleDto);
    }

    @DeleteMapping("/schedule/delete/{id}/{flightNumber}")
    public String deleteSchedule(@PathVariable int id,@PathVariable String flightNumber){
        return scheduleService.deleteSchedule(id, flightNumber);
    }

}
