package com.flightbooking.repos;

import com.flightbooking.Data.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatsRepository extends JpaRepository<Seats,Integer> {

    @Query(value = "SELECT * from Seats WHERE airline_number=?1",nativeQuery = true)
    Seats findByAirlineNumber(String flightNumber);

    @Query(value="SELECT * from Seats WHERE airline_schedule_id=?1",nativeQuery = true)
    Seats findByAirlineScheduleId(int id);

    @Query(value="SELECT * from Seats WHERE airline_schedule_id=?1 AND airline_number=?2",nativeQuery = true)
    Seats findSeatByAirlineScheduleIdAndFlightNumber(int id,String flightNumber);
}
