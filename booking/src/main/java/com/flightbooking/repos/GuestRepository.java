package com.flightbooking.repos;

import com.flightbooking.Data.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GuestRepository extends JpaRepository<Guest,Integer> {

    @Query(value="SELECT * from Guest WHERE email=?1",nativeQuery = true)
    Guest findIdByEmail(String email);

    @Query(value="SELECT email from Guest WHERE pnr_number?1",nativeQuery = true)
    String findEmailByPnr(String pnr);
}
