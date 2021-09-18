package com.flightbooking.repos;

import com.flightbooking.data.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    @Query(value="SELECT name from Admin",nativeQuery = true)
    List<String> findAllUserNames();

    @Query(value="SELECT * from Admin WHERE name=?1",nativeQuery = true)
    Admin findAdminByName(String userName);
}
