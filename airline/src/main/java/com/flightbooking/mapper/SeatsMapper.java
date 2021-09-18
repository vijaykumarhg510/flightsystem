package com.flightbooking.mapper;

import com.flightbooking.data.Seats;
import com.flightbooking.dtos.SeatsDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class SeatsMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Seats.class, SeatsDto.class).byDefault());
        factory.registerClassMap(factory.classMap(SeatsDto.class, Seats.class).byDefault());

    }
}
