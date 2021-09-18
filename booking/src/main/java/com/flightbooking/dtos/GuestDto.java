package com.flightbooking.dtos;

import lombok.Data;

@Data
public class GuestDto {

    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;
}
