package com.flightbooking.service;

import com.flightbooking.Data.Guest;
import com.flightbooking.Mapper.GuestMapper;
import com.flightbooking.dtos.GuestDto;
import com.flightbooking.repos.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;

    public GuestDto addUser(GuestDto guestDto){
        List<String> usernames = guestRepository.findAllUserNames();
        if(!usernames.contains(guestDto.getUserName())){
            Guest guest = guestMapper.map(guestDto,Guest.class);
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            String encodedPassword = b.encode(guestDto.getPassword());
            guest.setPassword(encodedPassword);
            guestRepository.save(guest);
            return guestDto;
        }else{
            System.out.println("USERNAME already exists, Please give other name");
        }
        return null;
    }

}
