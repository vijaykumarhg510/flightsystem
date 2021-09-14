package com.flightbooking.Data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNumber;
    private String pnr;
    private boolean business;
//    private String food = ()


//    private String email;

    @OneToOne(targetEntity = Airline.class)
    @JoinColumn(name="airline_number",referencedColumnName = "airlineNumber",nullable = false)
    private Airline airline;

    @OneToOne
    @JoinColumn(name="airline_schedule_id",referencedColumnName = "id",nullable = false)
    private AirlineSchedule airlineSchedule;


}
