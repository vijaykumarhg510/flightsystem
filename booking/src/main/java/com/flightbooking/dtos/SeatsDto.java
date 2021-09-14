package com.flightbooking.dtos;

import lombok.Data;

@Data
public class SeatsDto {

    private int busSeats;
    private int nonBusSeats;
    private double costOfBusSeat;
    private double costOfNonBusSeat;
    private int noOfRows;
    private boolean isFilled = false;
}
