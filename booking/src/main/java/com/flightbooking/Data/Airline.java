package com.flightbooking.Data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Airline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String airlineNumber;
    private String airlineName;
    private String phone;

    private String address;
    private String instrument;

    @OneToMany(mappedBy = "airline",targetEntity = AirlineSchedule.class)
    private Set<AirlineSchedule> airlineSchedule;

    @OneToOne(targetEntity = Discount.class,mappedBy = "airline")
    private Discount discount;

    @OneToMany(targetEntity = Booking.class,mappedBy = "airline")
    private List<Booking> booking;

    @OneToMany(targetEntity = Seats.class,mappedBy = "airline")
    private List<Seats> seats;

    public String getAirlineNumber() {
        return this.airlineNumber;
    }

    public void setAirlineNumber(String airlineNumber) {
        this.airlineNumber = airlineNumber;
    }
}
