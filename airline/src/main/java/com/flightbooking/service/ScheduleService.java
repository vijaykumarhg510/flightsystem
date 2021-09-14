package com.flightbooking.service;

import com.flightbooking.converter.TimeStampToLocalTime;
import com.flightbooking.data.Airline;
import com.flightbooking.data.AirlineSchedule;
import com.flightbooking.dtos.SeatsDto;
import com.flightbooking.repos.AirlineRepository;
import com.flightbooking.repos.AirlineScheduleRepository;
import com.flightbooking.dtos.AirlineScheduleDto;
import com.flightbooking.mapper.AirlineScheduleMapper;
import com.flightbooking.repos.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class ScheduleService {

    @Autowired
    private AirlineRepository repository;

    @Autowired
    private AirlineScheduleRepository airlineScheduleRepository;

    @Autowired
    private AirlineScheduleMapper airlineScheduleMapper;

    @Autowired
    private SeatsRepository seatsRepository;


    public AirlineScheduleDto addSchedule(String flightNumber,AirlineScheduleDto airlineScheduleDto){
        Airline schedulingAirline = repository.findByAirlineNumber(flightNumber);
        if(checkingSchedule(flightNumber, airlineScheduleDto)){
            AirlineSchedule airlineSchedule = airlineScheduleMapper.map(airlineScheduleDto,AirlineSchedule.class);
            airlineSchedule.setAirline(schedulingAirline);
            airlineScheduleRepository.save(airlineSchedule);
            return airlineScheduleDto;
        }else{
            System.out.println("Airline is already scheduled for the given time");
            return null;
        }

    }

    public SeatsDto addSeats(int airlineScheduleId, SeatsDto seatsDto){

        return null;
    }

    public List<Timestamp> findAllStartDateTime(String flightNumber){
        return airlineScheduleRepository.findStartDateTimeByAirlineNumber(flightNumber);
    }
    public List<Timestamp> findAllEndDateTime(String flightNumber){
        return airlineScheduleRepository.findEndDateTimeByAirlineNumber(flightNumber);
    }
    public boolean checkingSchedule(String flightNumber,AirlineScheduleDto airlineScheduleDto){
        LocalDateTime startTime = airlineScheduleDto.getStartDateTime();
        LocalDateTime endTime = airlineScheduleDto.getEndDateTime();
        List<Timestamp> startTimeStamp = findAllStartDateTime(flightNumber);
        TimeStampToLocalTime timeStampToLocalTime = new TimeStampToLocalTime();
        List<LocalDateTime> startDateTime = timeStampToLocalTime.fromTimeStampListToLocalTimeList(startTimeStamp);
        List<Timestamp> endTimeStamp = findAllEndDateTime(flightNumber);
        List<LocalDateTime> endDateTime = timeStampToLocalTime.fromTimeStampListToLocalTimeList(endTimeStamp);
        List<Integer> neg = new ArrayList<>();
       for(LocalDateTime end : endDateTime){
            neg.add(startTime.compareTo(end));
       }
       if(neg.contains(Integer.valueOf(-1))){
           return false;
       }else{
           return true;
       }
    }
    public List<AirlineScheduleDto> getAllSchedulesByFlightNumber(String flightNumber) {
        List<AirlineSchedule> airlineSchedules = airlineScheduleRepository.
                findAllByFlightNumber(flightNumber);
        return airlineSchedules.stream().map(airlineSchedule -> airlineScheduleMapper.
                map(airlineSchedule,AirlineScheduleDto.class)).collect(Collectors.toList());
    }

    public AirlineScheduleDto modifySchedule(int id,String flightNumber,AirlineScheduleDto airlineScheduleDto){
        Optional<AirlineSchedule> optionalAirlineSchedule =
                airlineScheduleRepository.findById(id);
        AirlineSchedule airlineSchedule = optionalAirlineSchedule.get();
        try{
            if(airlineSchedule.getAirline().getAirlineNumber().equals(flightNumber)){
                if(checkingSchedule(flightNumber,airlineScheduleDto)){
                    AirlineSchedule modifiedSchedule = airlineScheduleMapper.map(
                            airlineScheduleDto,AirlineSchedule.class);
                    airlineSchedule.setEndDateTime(modifiedSchedule.getEndDateTime());
                    airlineSchedule.setFromPlace(modifiedSchedule.getFromPlace());
                    airlineSchedule.setToPlace(modifiedSchedule.getToPlace());
                    airlineSchedule.setStartDateTime(modifiedSchedule.getStartDateTime());
                    airlineSchedule.setWorkingDays(modifiedSchedule.getWorkingDays());
                    airlineScheduleRepository.save(airlineSchedule);
                    return airlineScheduleDto;
                }else{
                    System.out.println("Airline is already scheduled for the given time");
                    return null;
                }
            }else{
                System.out.println("Given flight number is incorrect");
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return null;
    }

    public String deleteSchedule(int id,String flightNumber){
        Optional<AirlineSchedule> optionalAirlineSchedule =
                airlineScheduleRepository.findById(id);
        AirlineSchedule airlineSchedule = optionalAirlineSchedule.get();
        try{
            if(airlineSchedule.getAirline().getAirlineNumber().equals(flightNumber)){
                airlineScheduleRepository.delete(airlineSchedule);
                return airlineSchedule.getAirline().getAirlineName() +"-"+
                        airlineSchedule.getAirline().getAirlineNumber()+" scheduled from "+
                        airlineSchedule.getFromPlace()+" to " + airlineSchedule.getToPlace()+
                        " at "+ airlineSchedule.getStartDateTime()+" is cancelled";
            }else{
                System.out.println("Given flight number is incorrect");
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return null;
    }
}
