package com.flightbooking.data;

import lombok.Data;
import org.hibernate.cfg.annotations.reflection.XMLContext;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Airline implements Serializable {

    public static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String airlineNumber;
    private String airlineName;
    private String phone;

    private String address;
    private String instrument;


    @OneToMany(mappedBy = "airline",targetEntity = AirlineSchedule.class)
    private Set<AirlineSchedule> airlineSchedule;

    @OneToMany(targetEntity = Seats.class,mappedBy = "airline")
    private List<Seats> seats;

}
