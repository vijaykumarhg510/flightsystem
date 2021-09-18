package com.flightbooking.mapper;

import com.flightbooking.data.Admin;
import com.flightbooking.dtos.AdminDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Admin.class, AdminDto.class).byDefault());
        factory.registerClassMap(factory.classMap(AdminDto.class, Admin.class).byDefault());
    }
}
