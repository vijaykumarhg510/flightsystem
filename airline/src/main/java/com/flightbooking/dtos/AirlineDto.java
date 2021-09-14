package com.flightbooking.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AirlineDto {

    private String airlineName;
    private String airlineNumber;
    private String phone;
    private String instrument;
    private String address;



}
