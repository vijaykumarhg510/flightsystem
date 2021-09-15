package com.flightbooking.Data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String password;

    @OneToOne(targetEntity = Booking.class)
    @JoinColumn(name="pnr_number",referencedColumnName = "pnr")
    private Booking booking;

}
