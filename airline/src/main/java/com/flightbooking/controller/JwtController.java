package com.flightbooking.controller;

import com.flightbooking.config.JwtConfig;
import com.flightbooking.get.GetAdmin;
import com.flightbooking.mapper.AdminMapper;
import com.flightbooking.repos.AdminRepository;
import com.flightbooking.request.AdminRequest;
import com.flightbooking.response.AdminResponse;
import com.flightbooking.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GetAdmin getAdmin;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/authenticate")
    public ResponseEntity<AdminResponse> generateToken(@RequestBody AdminRequest adminRequest) throws Exception{
        authenticate(adminRequest.getName(),adminRequest.getPassword());
        final UserDetails userDetails = adminUserService.loadUserByUsername(adminRequest.getName());
        final String token = jwtConfig.generateToken(userDetails);
        return ResponseEntity.ok(new AdminResponse(token));
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
