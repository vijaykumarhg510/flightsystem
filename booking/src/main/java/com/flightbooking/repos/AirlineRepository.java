package com.flightbooking.repos;

import com.flightbooking.Data.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirlineRepository extends JpaRepository<Airline,Integer> {

    @Query(value="SELECT * from Airline WHERE airline_number=?1",nativeQuery = true)
    Airline findByAirlineNumber(String flightNumber);
}
