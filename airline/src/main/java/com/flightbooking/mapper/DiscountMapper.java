package com.flightbooking.mapper;

import com.flightbooking.data.Discount;
import com.flightbooking.dtos.DiscountDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Discount.class, DiscountDto.class).byDefault());
        factory.registerClassMap(factory.classMap(DiscountDto.class, Discount.class).byDefault());

    }
}
