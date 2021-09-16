package com.flightbooking.Data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    private String airlineNumber;
    private String airlineName;
    private String phone;

    private String address;
    private String instrument;

    @OneToMany(mappedBy = "airline",targetEntity = AirlineSchedule.class)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    private Set<AirlineSchedule> airlineSchedule;

//    @OneToOne(mappedBy = "airline",targetEntity = Discount.class)
//    private Discount discount;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = Booking.class,mappedBy = "airline")
    private List<Booking> booking;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = Seats.class,mappedBy = "airline")
    private List<Seats> seats;

    public String getAirlineNumber() {
        return this.airlineNumber;
    }

    public void setAirlineNumber(String airlineNumber) {
        this.airlineNumber = airlineNumber;
    }
}
