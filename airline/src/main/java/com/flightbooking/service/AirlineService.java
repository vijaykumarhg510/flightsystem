package com.flightbooking.service;

import com.flightbooking.controller.AirlineController;
import com.flightbooking.data.Airline;
import com.flightbooking.repos.AirlineRepository;
import com.flightbooking.dtos.AirlineDto;
import com.flightbooking.mapper.AirlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirlineMapper airlineMapper;

    @Autowired
    private AirlineController airlineController;

    public AirlineDto addAirline(AirlineDto airlineDto){
        if(airlineDto.getAirlineName().isEmpty()){
            Airline airline = airlineMapper.map(airlineDto,Airline.class);
            airlineRepository.save(airline);
            AirlineDto savedAirlineDto = airlineMapper.map(airline,AirlineDto.class);
            return savedAirlineDto;
        }else{
            System.out.println("please enter the valid airline name");
        }

    }

    public AirlineDto modifyAirline(int id,AirlineDto airlineDto){
        Optional<Airline> optionalAirline = airlineRepository.findById(id);
        Airline airline = optionalAirline.get();
        Airline modifiedAirline = airlineMapper.map(airlineDto,Airline.class);
        airline.setAirlineName(airlineDto.getAirlineName());
        airline.setAirlineNumber(airlineDto.getAirlineNumber());
        airline.setPhone(airlineDto.getPhone());
        airline.setInstrument(airlineDto.getInstrument());
        airline.setAddress(airlineDto.getAddress());
        airlineRepository.save(airline);
        return airlineDto;
    }

    public List<Airline> getAllAirlines(){
        List<Airline> allAirlines = airlineRepository.findAll();
        return allAirlines;
    }

    public String deleteByFlightNumber(String flightNumber){
        List<String> flightNumbers = airlineRepository.findAllFlightNumbers();
        try{
            if(flightNumbers.contains(flightNumber)){
                Airline airline = airlineRepository.findByAirlineNumber(flightNumber);
                AirlineDto airlineDto = airlineMapper.map(airline,AirlineDto.class);
                airlineRepository.delete(airline);
                return airlineDto.getAirlineName() + "-" + airlineDto.getAirlineNumber() + "is deleted";
            }
        }catch (Exception e){
            System.out.println("Entered flight number is not found,Please check the number");
        }
        return null;
    }
}
