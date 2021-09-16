package com.flightbooking.service;

import com.flightbooking.Data.*;
import com.flightbooking.Mapper.BookingMapper;
import com.flightbooking.count.SeatsCount;
import com.flightbooking.dtos.BookingDto;
import com.flightbooking.get.GetAirlineScheduleByFlightNumber;
import com.flightbooking.repos.*;
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

    @Autowired
    private SeatsCount seatsCount;

    @Autowired
    private GuestRepository guestRepository;

    public BookingDto createBookingByFlightNumber(String flightNumber,BookingDto bookingDto){
        try{
            Booking booking = bookingMapper.map(bookingDto,Booking.class);
            AirlineSchedule airlineSchedule = get.getAirlineSchedule(flightNumber);
             Seats seats = seatsRepository.findByAirlineScheduleId(airlineSchedule.getId());
            if(!seatsCount.isSeatsFilled(seats)){
                double totalCost = seatsCount.seatCost(seats,bookingDto);
                Airline airline = airlineRepository.findByAirlineNumber(flightNumber);
                booking.setAirline(airline);
                booking.setAirlineSchedule(airlineSchedule);
                booking.setPnr(UUID.randomUUID().toString());
                booking.setSeatNumber(UUID.randomUUID().toString());
                booking.setTotalCost(totalCost);
                Guest guest = guestRepository.findIdByEmail(bookingDto.getEmail());
                guest.setBooking(booking);
                booking.setGuest(guest);
                bookingDto = bookingMapper.map(booking,BookingDto.class);
                bookingRepository.save(booking);
                seatsCount.setSeatsCount(seats);
                return bookingDto;
            }else{
                System.out.println("Airline is filled, Please select any other schedule");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public BookingDto modifyBookingByFlightNumber(String flightNumber,BookingDto bookingDto){
        Booking booking = bookingRepository.findByAirlineNumber(flightNumber);
        booking.setPnr(UUID.randomUUID().toString());
        booking.setSeatNumber(UUID.randomUUID().toString());
        bookingRepository.save(booking);
        return bookingDto;
    }

    public BookingDto getBookingByPnr(String pnr){
       Booking booking = bookingRepository.findByPnr(pnr);
       BookingDto bookingDto = bookingMapper.map(booking,BookingDto.class);
       String email = guestRepository.findEmailByPnr(pnr);
       bookingDto.setEmail(email);
       return bookingDto;
    }

}
