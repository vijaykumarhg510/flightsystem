package com.flightbooking.controller;

import com.flightbooking.Mapper.GuestMapper;
import com.flightbooking.config.JwtConfig;
import com.flightbooking.data.Guest;
import com.flightbooking.dto.GuestDto;
import com.flightbooking.getter.GetGuest;
import com.flightbooking.repos.GuestRepository;
import com.flightbooking.request.GuestRequest;
import com.flightbooking.response.GuestResponse;
import com.flightbooking.service.GuestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GetGuest getGuest;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private GuestUserService guestUserService;

    @PostMapping("/authenticate")
    public ResponseEntity<GuestResponse> generateToken(@RequestBody GuestRequest guestRequest) throws Exception{
        authenticate(guestRequest.getUserName(),guestRequest.getPassword());
        final UserDetails userDetails = guestUserService.loadUserByUsername(guestRequest.getUserName());
        final String token = jwtConfig.generateToken(userDetails);
        return ResponseEntity.ok(new GuestResponse(token));
    }
    private void authenticate(String username,String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException ex){
            throw new Exception("USER DISABLED ",ex);
        }catch (BadCredentialsException ex){
            throw new Exception("INVALID CREDENTIALS",ex);
        }
    }
}
