package com.flightbooking.service;

import com.flightbooking.data.Guest;
import com.flightbooking.getter.GetGuest;
import com.flightbooking.repos.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GuestUserService implements UserDetailsService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GetGuest getGuest;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Guest guest = getGuest.getGuestByUserName(userName);
        if(guest.getUserName().equals(userName)){
            return new User(guest.getUserName(),guest.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found : "+userName);
        }
    }
}
