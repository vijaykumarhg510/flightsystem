package com.flightbooking.request;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class GuestRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

}
