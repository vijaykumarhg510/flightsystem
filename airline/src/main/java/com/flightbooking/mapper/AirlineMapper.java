package com.flightbooking.mapper;


import com.flightbooking.data.Airline;
import com.flightbooking.dtos.AirlineDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AirlineMapper extends ConfigurableMapper{
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(AirlineDto.class, Airline.class).byDefault()
                );
        factory.registerClassMap(factory.classMap(Airline.class, AirlineDto.class).byDefault());
    }

}
