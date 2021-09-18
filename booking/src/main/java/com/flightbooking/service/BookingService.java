package com.flightbooking.service;

import com.flightbooking.Data.*;
import com.flightbooking.Mapper.BookingMapper;
import com.flightbooking.config.JwtConfig;
import com.flightbooking.count.SeatsCount;
import com.flightbooking.dtos.BookingDto;
import com.flightbooking.get.GetAirlineScheduleByFlightNumber;
import com.flightbooking.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.util.*;

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

    @Autowired
    private JwtConfig jwtConfig;

    public BookingDto createBookingByFlightNumber(String flightNumber, BookingDto bookingDto,
                                                  HttpServletRequest httpServletRequest){
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
//                String header = httpServletRequest.getHeader("Authorization");
//                String token = header.substring(7);
//                String userName = jwtConfig.getUsernameFromToken(token);
               Guest guest = guestRepository.findIdByEmail(bookingDto.getEmail());
//                Guest guest = guestRepository.findGuestByName(userName);
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

    public List<Booking> getAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return bookings;
    }

    public BookingDto deleteBookingByPnr(String pnr){
        Booking booking = bookingRepository.findByPnr(pnr);
        BookingDto bookingDto = bookingMapper.map(booking,BookingDto.class);
        bookingRepository.delete(booking);
        return bookingDto;
    }

}
