package com.flightbooking.repos;

import com.flightbooking.Data.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {

    @Query(value="SELECT * from Discount WHERE airline_number=?1",nativeQuery = true)
    Discount findByAirlineNumber(String flightNumber);
}
