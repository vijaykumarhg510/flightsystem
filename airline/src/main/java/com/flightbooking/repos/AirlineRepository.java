package com.flightbooking.repos;

import com.flightbooking.data.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AirlineRepository extends JpaRepository<Airline,Integer> {

     Airline findByAirlineNumber(String airlineNumber);


     @Query(value="SELECT flightNumber from Airline",nativeQuery = true)
     List<String> findAllFlightNumbers();

}
