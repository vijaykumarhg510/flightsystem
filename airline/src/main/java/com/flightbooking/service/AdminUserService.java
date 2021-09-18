package com.flightbooking.service;

import com.flightbooking.data.Admin;
import com.flightbooking.get.GetAdmin;
import com.flightbooking.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminUserService implements UserDetailsService {
    @Autowired
    private AdminRepository guestRepository;

    @Autowired
    private GetAdmin getAdmin;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Admin admin = getAdmin.getGuestByUserName(userName);
        if(admin.getName().equals(userName)){
            return new User(admin.getName(),admin.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found : "+userName);
        }
    }
}
