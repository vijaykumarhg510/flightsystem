package com.flightbooking.get;

import com.flightbooking.Data.Guest;
import com.flightbooking.repos.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetGuest {
    @Autowired
    private GuestRepository guestRepository;

    public Guest getGuestByUserName(String userName){
        try{
            List<String> getGuests = guestRepository.findAllUserNames();
            if(getGuests.contains(userName)){
                Guest guest = guestRepository.findGuestByName(userName);
                return guest;
            }else{
                System.out.println("User doesn't exist");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
