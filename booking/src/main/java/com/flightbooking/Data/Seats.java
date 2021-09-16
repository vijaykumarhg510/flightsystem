package com.flightbooking.Data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Seats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int busSeats;
    private int nonBusSeats;
    private double costOfBusSeat;
    private double costOfNonBusSeat;
    private int noOfRows;
    private boolean isFilled = false;

    @ManyToOne(targetEntity = Airline.class)
    @JoinColumn(name="airline_number",referencedColumnName = "airlineNumber",nullable = false)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    private Airline airline;

    @OneToOne(targetEntity = AirlineSchedule.class)
    @JoinColumn(name="airline_schedule_id",referencedColumnName = "id",nullable = false)
    private AirlineSchedule airlineSchedule;

}
