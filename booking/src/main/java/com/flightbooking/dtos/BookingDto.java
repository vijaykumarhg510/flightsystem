package com.flightbooking.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;

@Data
@Component
public class BookingDto {

    //need to link with Guest from admin service
    private String email;
    private boolean business;
    private boolean veg;
}
