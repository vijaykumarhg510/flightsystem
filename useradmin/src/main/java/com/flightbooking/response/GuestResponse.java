package com.flightbooking.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GuestResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

}
