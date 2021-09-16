package com.flightbooking.repos;

import com.flightbooking.Data.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {
}
