package com.flightbooking.controller;

import com.flightbooking.dtos.BookingDto;
import com.flightbooking.service.BookingService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/booking")
@RestController
public class BookingController {

   @Autowired
    BookingService bookingService;

    @PostMapping({"/add/{flightNumber}"})
    public BookingDto createBookingByFlightNumber(@PathVariable String flightNumber){
        return bookingService.createBookingByFlightNumber(flightNumber);
    }

    @PutMapping("/edit/{flightNumber}")
    public BookingDto modifyBookingByFlightNumber(@PathVariable String flightNumber){
        return bookingService.modifyBookingByFlightNumber(flightNumber);
    }

    @GetMapping("/getAll")
    public List<BookingDto> getAllBookings(){
        return null;
    }
}
