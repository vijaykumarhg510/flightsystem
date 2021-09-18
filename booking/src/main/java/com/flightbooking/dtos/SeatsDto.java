package com.flightbooking.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SeatsDto {
    private int busSeats;
    private int nonBusSeats;
    private double costOfBusSeat;
    private double costOfNonBusSeat;
    private int noOfRows;
    private boolean isFilled = false;
}
