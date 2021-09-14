package com.flightbooking.data;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int busSeats;
    private int nonBusSeats;
    private double costOfBusSeat;
    private double costOfNonBusSeat;
    private int noOfRows;
    private boolean isFilled = false;

    @OneToOne(targetEntity = Airline.class)
    @JoinColumn(name="airline_number",referencedColumnName = "airlineNumber",nullable = false)
    private Airline airline;

    @OneToOne(targetEntity = AirlineSchedule.class)
    @JoinColumn(name="airline_schedule_id",referencedColumnName = "id",nullable = false)
    private AirlineSchedule airlineSchedule;

}
