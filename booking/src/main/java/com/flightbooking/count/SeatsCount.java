package com.flightbooking.count;

import com.flightbooking.Data.AirlineSchedule;
import com.flightbooking.Data.Seats;
import com.flightbooking.dtos.BookingDto;
import com.flightbooking.repos.DiscountRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class SeatsCount {
    private int totalSeats;
    private double totalCost;
    private Integer count = 0;
    private Map<Integer,Integer> seatsCount = new HashMap<>();
//    private DiscountRepository discountRepository;

    public boolean isSeatsFilled(Seats seats){
       Integer id = Integer.valueOf(seats.getId());
       Integer totalSeats = Integer.valueOf(seats.getBusSeats()+seats.getNonBusSeats());
        Integer count = seatsCount.get(id);
       if(!seatsCount.isEmpty()){
           if(count<totalSeats){
               return false;
           }else{
               return true;
           }
       }else{
           return false;
       }
    }

    public void setSeatsCount(Seats seats){
        Integer id = Integer.valueOf(seats.getId());
         seatsCount.put(id,count++);
    }

    public double seatCost(Seats seats, BookingDto bookingDto){
       totalSeats = seats.getNonBusSeats()+ seats.getBusSeats();
       if(bookingDto.isBusiness()){
           if(bookingDto.isVeg()){
               return totalCost = seats.getCostOfBusSeat()+200.00;
           }else{
               return totalCost = seats.getCostOfBusSeat()+500.00;
           }
       }else{
           if(bookingDto.isVeg()){
               return totalCost = seats.getCostOfNonBusSeat()+200.00;
           }else{
               return totalCost = seats.getCostOfNonBusSeat()+500.00;
           }
       }
    }
}
