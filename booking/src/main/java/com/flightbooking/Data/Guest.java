package com.flightbooking.Data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Guest implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;

    @OneToOne(targetEntity = Booking.class)
    @JoinColumn(name="pnr_number",referencedColumnName = "pnr")
    private Booking booking;

}
