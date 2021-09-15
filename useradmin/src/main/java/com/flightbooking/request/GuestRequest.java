package com.flightbooking.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GuestRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;


}
