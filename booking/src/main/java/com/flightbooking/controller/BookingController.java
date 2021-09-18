package com.flightbooking.controller;

import com.flightbooking.Data.Booking;
import com.flightbooking.dtos.BookingDto;
import com.flightbooking.service.BookingService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/users/booking")
@RestController
public class BookingController {

   @Autowired
    BookingService bookingService;


    @PostMapping({"/add/{flightNumber}"})
    public BookingDto createBookingByFlightNumber(@PathVariable String flightNumber,@RequestBody BookingDto bookingDto,
    HttpServletRequest httpservletrequest){
        return bookingService.createBookingByFlightNumber(flightNumber,bookingDto,httpservletrequest);
    }

    @PutMapping("/edit/{flightNumber}")
    public BookingDto modifyBookingByFlightNumber(@PathVariable String flightNumber,@RequestBody BookingDto bookingDto){
        return bookingService.modifyBookingByFlightNumber(flightNumber,bookingDto);
    }

    @GetMapping("/get/{pnr}")
    public BookingDto getBookingByPnr(@PathVariable String pnr){

        return bookingService.getBookingByPnr(pnr);
    }

    @GetMapping("/getAll")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/delete/{pnr}")
    public BookingDto deleteBookingByPnr(@PathVariable String pnr){
        return bookingService.deleteBookingByPnr(pnr);
    }
}
