package com.flightbooking.Mapper;

import com.flightbooking.data.Guest;
import com.flightbooking.dto.GuestDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Guest.class, GuestDto.class).byDefault());
        factory.registerClassMap(factory.classMap(GuestDto.class, Guest.class).byDefault());
    }
}
