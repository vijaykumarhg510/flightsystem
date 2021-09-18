package com.flightbooking.service;

import com.flightbooking.data.Discount;
import com.flightbooking.dtos.DiscountDto;
import com.flightbooking.mapper.DiscountMapper;
import com.flightbooking.repos.AirlineRepository;
import com.flightbooking.repos.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountService {

        @Autowired
        private AirlineRepository airlineRepository;

        @Autowired
        private DiscountMapper discountMapper;

        @Autowired
        private DiscountRepository discountRepository;

        public DiscountDto addDiscount(DiscountDto discountDto){
            try{
                Discount discount = discountMapper.map(discountDto, Discount.class);
//            Airline airline = airlineRepository.findByAirlineNumber(flightNumber);
//            discount.setAirline(airline);
                discountRepository.save(discount);
                return discountDto;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }

        public DiscountDto modifyDiscount(int id,DiscountDto discountDto){
            try{
//            Discount discount = discountRepository.findByAirlineNumber(flightNumber);
                Optional<Discount> optionalDiscount = discountRepository.findById(id);
                Discount discount = optionalDiscount.get();
                discount.setDiscountCode(discountDto.getDiscountCode());
                discount.setDiscountAmount(discountDto.getDiscountAmount());
                discountRepository.save(discount);
                return discountDto;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }

        public List<DiscountDto> getAllDiscounts(){
            try{
                List<Discount> discounts = discountRepository.findAll();
                List<DiscountDto> discountDtos = discounts.stream().map(d->
                        discountMapper.map(d,DiscountDto.class)).collect(Collectors.toList());
                return discountDtos;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }
        public DiscountDto deleteDiscountByFlightNumber(int id){
            try{
//            Discount discount = discountRepository.findByAirlineNumber(flightNumber);
                Optional<Discount> optionalDiscount = discountRepository.findById(id);
                Discount discount = optionalDiscount.get();
                DiscountDto discountDto = discountMapper.map(discount,DiscountDto.class);
                discountRepository.delete(discount);
                return discountDto;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }


}
