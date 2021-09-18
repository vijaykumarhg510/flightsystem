package com.flightbooking.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;
}
