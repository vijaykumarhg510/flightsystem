package com.flightbooking.service;

import com.flightbooking.Data.Airline;
import com.flightbooking.Data.Discount;
import com.flightbooking.Mapper.DiscountMapper;
import com.flightbooking.dtos.DiscountDto;
import com.flightbooking.repos.AirlineRepository;
import com.flightbooking.repos.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private DiscountRepository discountRepository;

    public DiscountDto addDiscount(String flightNumber,DiscountDto discountDto){
        try{
            Discount discount = discountMapper.map(discountDto,Discount.class);
            Airline airline = airlineRepository.findByAirlineNumber(flightNumber);
            discount.setAirline(airline);
            discountRepository.save(discount);
            return discountDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       return null;
    }

    public DiscountDto modifyDiscount(String flightNumber,DiscountDto discountDto){
        try{
            Discount discount = discountRepository.findByAirlineNumber(flightNumber);
            discount.setDiscountCode(discountDto.getDiscountCode());
            discount.setDiscountAmount(discountDto.getDiscountAmount());
            discountRepository.save(discount);
            return discountDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DiscountDto getDiscountByFlightNumber(String flightNumber){
        try{
           Discount discount = discountRepository.findByAirlineNumber(flightNumber);
           DiscountDto discountDto = discountMapper.map(discount,DiscountDto.class);
           return discountDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public DiscountDto deleteDiscountByFlightNumber(String flightNumber){
        try{
            Discount discount = discountRepository.findByAirlineNumber(flightNumber);
            DiscountDto discountDto = discountMapper.map(discount,DiscountDto.class);
            discountRepository.delete(discount);
            return discountDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
