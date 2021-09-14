package com.flightbooking.repos;

import com.flightbooking.Data.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    @Query(value="SELECT * from Booking WHERE airline_number=?1",nativeQuery = true)
    Booking findByAirlineNumber(String flightNumber);
}
