package com.flightbooking.dtos;

import lombok.Data;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

@Data
public class DiscountDto implements Serializable {
    private static final long serailVersionUID=1L;

    private double discountAmount;
    private String discountCode;
}
