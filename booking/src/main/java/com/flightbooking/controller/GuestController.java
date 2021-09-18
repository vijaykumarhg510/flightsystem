package com.flightbooking.controller;

import com.flightbooking.dtos.GuestDto;
import com.flightbooking.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/add")
    public GuestDto addUser(@RequestBody GuestDto guestDto){

        return guestService.addUser(guestDto);
    }
}
