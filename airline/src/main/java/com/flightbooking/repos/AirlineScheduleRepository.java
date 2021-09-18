package com.flightbooking.repos;

import com.flightbooking.data.AirlineSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;


public interface AirlineScheduleRepository extends JpaRepository<AirlineSchedule,Integer> {

    @Query(value = "SELECT start_Date_Time from Airline_Schedule a WHERE a.airline_number=?1 ",
            nativeQuery = true)
    List<Timestamp> findStartDateTimeByAirlineNumber(String flightNumber);

    @Query(value = "SELECT end_Date_Time from Airline_Schedule a WHERE a.airline_number=?1",
           nativeQuery = true)
    List<Timestamp> findEndDateTimeByAirlineNumber(String flightNumber);

    @Query(value = "SELECT * from Airline_Schedule a WHERE a.airline_number=?1",
            nativeQuery = true)
    List<AirlineSchedule> findAllByFlightNumber(String flightNumber);

    @Query(value="SELECT * from Airline_Schedule WHERE airline_number=?1",nativeQuery = true)
    List<AirlineSchedule> findByAirlineNumber(String flightNumber);

}
