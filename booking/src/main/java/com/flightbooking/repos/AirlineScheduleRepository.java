package com.flightbooking.repos;

import com.flightbooking.Data.AirlineSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirlineScheduleRepository extends JpaRepository<AirlineSchedule,Integer> {

    @Query(value="SELECT * from Airline_Schedule WHERE airline_number=?1",nativeQuery = true)
    List<AirlineSchedule> findByAirlineNumber(String flightNumber);

}
