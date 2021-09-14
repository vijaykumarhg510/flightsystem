package com.flightbooking.get;

import com.flightbooking.Data.AirlineSchedule;
import com.flightbooking.repos.AirlineScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class GetAirlineScheduleByFlightNumber {

    @Autowired
    private AirlineScheduleRepository airlineScheduleRepository;

    public AirlineSchedule getAirlineSchedule(String flightNumber){
        try{
            List<AirlineSchedule> airlineSchedules = airlineScheduleRepository.
                    findByAirlineNumber(flightNumber);
            System.out.println(airlineSchedules.toString());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the id corresponding to the needed schedule");
            if(scanner.hasNextInt()) {
                Optional<AirlineSchedule> optionalAirlineSchedule = airlineScheduleRepository.
                        findById(scanner.nextInt());
                AirlineSchedule airlineSchedule = optionalAirlineSchedule.get();
                return airlineSchedule;

            }else{
                System.out.println("Invalid input, Enter the correct id in numbers");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
