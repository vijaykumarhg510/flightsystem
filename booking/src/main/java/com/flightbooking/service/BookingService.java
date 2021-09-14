package com.flightbooking.service;

import com.flightbooking.Data.Booking;
import com.flightbooking.Data.Seats;
import com.flightbooking.Mapper.BookingMapper;
import com.flightbooking.dtos.BookingDto;
import com.flightbooking.get.GetAirlineScheduleByFlightNumber;
import com.flightbooking.repos.AirlineRepository;
import com.flightbooking.repos.AirlineScheduleRepository;
import com.flightbooking.repos.BookingRepository;
import com.flightbooking.Data.Airline;
import com.flightbooking.Data.AirlineSchedule;
import com.flightbooking.repos.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirlineScheduleRepository airlineScheduleRepository;

    @Autowired
    private  GetAirlineScheduleByFlightNumber get;

    @Autowired
    private SeatsRepository seatsRepository;


    public BookingDto createBookingByFlightNumber(String flightNumber){
        try{
            AirlineSchedule airlineSchedule = get.getAirlineSchedule(flightNumber);
            Seats seats = seatsRepository.findByAirlineScheduleId(airlineSchedule.getId());
            if()
                Airline airline = airlineRepository.findByAirlineNumber(flightNumber);
                Booking booking = new Booking();
                booking.setAirline(airline);
                booking.setAirlineSchedule(airlineSchedule);
                booking.setPnr(UUID.randomUUID().toString());
                booking.setSeatNumber(UUID.randomUUID().toString());
                BookingDto bookingDto = bookingMapper.map(booking,BookingDto.class);
                bookingRepository.save(booking);
                return bookingDto;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public BookingDto modifyBookingByFlightNumber(String flightNumber){
        Booking booking = bookingRepository.findByAirlineNumber(flightNumber);
        booking.setPnr(UUID.randomUUID().toString());
        booking.setSeatNumber(UUID.randomUUID().toString());
        bookingRepository.save(booking);
        BookingDto bookingDto = bookingMapper.map(booking,BookingDto.class);
        return bookingDto;
    }

}
