package com.flightbooking.repos;

import com.flightbooking.data.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SeatsRepository extends JpaRepository<Seats,Integer> {

}
