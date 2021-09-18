package com.flightbooking.controller;


import com.flightbooking.dtos.AdminDto;
import com.flightbooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public String saveAdmin(@RequestBody AdminDto adminDto){
        return adminService.saveAdmin(adminDto);
    }
}
