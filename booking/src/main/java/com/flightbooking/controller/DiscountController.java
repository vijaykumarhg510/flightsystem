package com.flightbooking.controller;

import com.flightbooking.dtos.DiscountDto;
import com.flightbooking.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/add/{flightNumber}")
    public DiscountDto addDiscount(@PathVariable String flightNumber,@RequestBody DiscountDto discountDto){

        return discountService.addDiscount(flightNumber, discountDto);
    }
    @PutMapping("/edit/{flightNumber}")
    public DiscountDto modifyDiscount(@PathVariable String flightNumber,@RequestBody DiscountDto discountDto){

        return discountService.modifyDiscount(flightNumber, discountDto);
    }

    @GetMapping("/getDiscount/{flightNumber}")
    public DiscountDto getDiscountByFlightNumber(@PathVariable String flightNumber){

        return discountService.getDiscountByFlightNumber(flightNumber);
    }
    @DeleteMapping("/delete/{flightNumber}")
    public DiscountDto deleteDiscountByFlightNumber(@PathVariable String flightNumber){

        return discountService.deleteDiscountByFlightNumber(flightNumber);
    }

}

