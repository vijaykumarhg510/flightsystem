package com.flightbooking.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiscountDto implements Serializable {

        private static final long serialVersionUID=1L;

        private double discountAmount;
        private String discountCode;


}
