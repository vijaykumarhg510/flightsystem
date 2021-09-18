package com.flightbooking.get;

import com.flightbooking.data.Admin;
import com.flightbooking.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAdmin {
    @Autowired
    private AdminRepository adminRepository;

    public Admin getGuestByUserName(String userName){
        try{
            List<String> getAdmin = adminRepository.findAllUserNames();
            if(getAdmin.contains(userName)){
                Admin admin = adminRepository.findAdminByName(userName);
                return admin;
            }else{
                System.out.println("User doesn't exist");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
