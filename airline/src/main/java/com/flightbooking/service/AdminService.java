package com.flightbooking.service;

import com.flightbooking.data.Admin;
import com.flightbooking.dtos.AdminDto;
import com.flightbooking.mapper.AdminMapper;
import com.flightbooking.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    public String saveAdmin(AdminDto adminDto){
        if(!(adminDto.getName().isEmpty() && adminDto.getPassword().isEmpty())){
         List<String> names = adminRepository.findAllUserNames();
            if(!names.contains(adminDto.getName())){
                Admin admin = adminMapper.map(adminDto,Admin.class);
                BCryptPasswordEncoder b = new BCryptPasswordEncoder();
                String encodedPassword = b.encode(adminDto.getPassword());
                admin.setPassword(encodedPassword);
                adminRepository.save(admin);
                return "ADMIN HAS BEEN SAVED SUCCESSFULLY IN DB";
            }else{
                return "USERNAME already exists, Please give other name";
            }
        }else{
            System.out.println("please enter the valid input");
            return "ERROR SAVING THE ADMIN";
        }
    }
}
