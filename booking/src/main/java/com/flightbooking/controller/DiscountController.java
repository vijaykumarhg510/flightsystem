package com.flightbooking.controller;

import com.flightbooking.dtos.DiscountDto;
import com.flightbooking.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/add")
    public DiscountDto addDiscount(@RequestBody DiscountDto discountDto){

        return discountService.addDiscount( discountDto);
    }
    @PutMapping("/edit/{id}")
    public DiscountDto modifyDiscount(@PathVariable int id,@RequestBody DiscountDto discountDto){

        return discountService.modifyDiscount(id, discountDto);
    }

    @GetMapping("/getDiscount")
    public List<DiscountDto> getDiscountByFlightNumber(){

        return discountService.getAllDiscounts();
    }
    @DeleteMapping("/delete/{id}")
    public DiscountDto deleteDiscountByFlightNumber(@PathVariable int id){

        return discountService.deleteDiscountByFlightNumber(id);
    }

}

