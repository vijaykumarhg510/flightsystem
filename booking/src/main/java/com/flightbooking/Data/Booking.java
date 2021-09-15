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
    private boolean veg;
    private double totalCost;

    @OneToOne(targetEntity = Guest.class)
    @JoinColumn(name="guest_id",referencedColumnName = "id",nullable = false)
    private Guest guest;

    @ManyToOne(targetEntity = Airline.class)
    @JoinColumn(name="airline_number",referencedColumnName = "airlineNumber",nullable = false)
    private Airline airline;

    @OneToOne(targetEntity = AirlineSchedule.class)
    @JoinColumn(name="airline_schedule_id",referencedColumnName = "id",nullable = false)
    private AirlineSchedule airlineSchedule;


}
