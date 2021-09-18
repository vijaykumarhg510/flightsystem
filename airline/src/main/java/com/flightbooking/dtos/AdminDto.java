package com.flightbooking.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdminDto {

    private String name;
    private String password;

}
