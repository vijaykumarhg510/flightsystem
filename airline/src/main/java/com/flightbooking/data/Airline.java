package com.flightbooking.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Airline implements Serializable {

    public static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String airlineNumber;
    private String airlineName;
    private String phone;

    private String address;
    private String instrument;



    @OneToMany(mappedBy = "airline")
    private Set<AirlineSchedule> airlineSchedule;

}
