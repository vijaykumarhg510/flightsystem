package com.flightbooking.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;

@Data
@Component
public class BookingDto {

    private String seatNumber;
    private String pnr;
    private boolean business;
}
