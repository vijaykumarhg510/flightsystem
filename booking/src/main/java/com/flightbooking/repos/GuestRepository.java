package com.flightbooking.repos;

import com.flightbooking.Data.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Integer> {
}
