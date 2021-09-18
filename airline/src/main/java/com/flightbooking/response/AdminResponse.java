package com.flightbooking.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AdminResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
}
