package com.flightbooking.Mapper;

import com.flightbooking.Data.Booking;
import com.flightbooking.dtos.BookingDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Booking.class, BookingDto.class).
                byDefault());
        factory.registerClassMap(factory.classMap(BookingDto.class,Booking.class).byDefault());

    }
}
