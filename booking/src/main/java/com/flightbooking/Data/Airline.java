package com.flightbooking.Data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Airline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String airlineNumber;
    private String airlineName;
    private String phone;

    private String address;
    private String instrument;

    @OneToMany()
    private Set<AirlineSchedule> airlineSchedule;
}
