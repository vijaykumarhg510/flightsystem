package com.flightbooking.service;

import com.flightbooking.data.Airline;
import com.flightbooking.data.AirlineSchedule;
import com.flightbooking.data.Seats;
import com.flightbooking.dtos.SeatsDto;
import com.flightbooking.get.GetAirlineScheduleByFlightNumber;
import com.flightbooking.mapper.SeatsMapper;
import com.flightbooking.repos.AirlineRepository;
import com.flightbooking.repos.AirlineScheduleRepository;
import com.flightbooking.repos.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatsService {


        @Autowired
        private SeatsRepository seatsRepository;

        @Autowired
        private AirlineRepository airlineRepository;

        @Autowired
        private AirlineScheduleRepository airlineScheduleRepository;

        @Autowired
        private SeatsMapper seatsMapper;

        @Autowired
        private GetAirlineScheduleByFlightNumber get;


        public SeatsDto addSeats(String flightNumber, SeatsDto seatsDto){
            try{
                AirlineSchedule airlineSchedule = get.getAirlineSchedule(flightNumber);
                Airline airline = airlineRepository.findByAirlineNumber(flightNumber);
                Seats seats = seatsMapper.map(seatsDto,Seats.class);
                seats.setAirline(airline);
                seats.setAirlineSchedule(airlineSchedule);
                seatsRepository.save(seats);
                Seats dbSeats = seatsRepository.findSeatByAirlineScheduleIdAndFlightNumber(
                        airlineSchedule.getId(),flightNumber);
                airlineSchedule.setSeats(dbSeats);
                airlineScheduleRepository.save(airlineSchedule);
                return seatsDto;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }

        public SeatsDto modifySeats(String flightNumber,SeatsDto seatsDto){
            try{
                Seats seats = seatsRepository.findByAirlineNumber(flightNumber);
                Seats modifiedSeat = seatsMapper.map(seatsDto,Seats.class);
                seats.setBusSeats(seatsDto.getBusSeats());
                seats.setNonBusSeats(seats.getNonBusSeats());
                seats.setCostOfBusSeat(seats.getCostOfBusSeat());
                seats.setCostOfNonBusSeat(seats.getCostOfNonBusSeat());
                seats.setNoOfRows(seatsDto.getNoOfRows());
                seatsRepository.save(seats);
                return seatsDto;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }

        public SeatsDto getSeatsByFlightNumber(String flightNumber){
            AirlineSchedule airlineSchedule = get.getAirlineSchedule(flightNumber);
            Seats seats = seatsRepository.findByAirlineScheduleId(airlineSchedule.getId());
            SeatsDto seatsDto = seatsMapper.map(seats,SeatsDto.class);
            return seatsDto;
        }

        public SeatsDto deleteSeatsByFlightNumber(String flightNumber){
            AirlineSchedule airlineSchedule = get.getAirlineSchedule(flightNumber);
            Seats seats = seatsRepository.findByAirlineScheduleId(airlineSchedule.getId());
            SeatsDto seatsDto = seatsMapper.map(seats,SeatsDto.class);
            seatsRepository.delete(seats);
            return seatsDto;
        }


}
