package com.flightbooking.mapper;

import com.flightbooking.data.AirlineSchedule;
import com.flightbooking.dtos.AirlineScheduleDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AirlineScheduleMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
//        factory.registerClassMap(factory.classMap(AirlineSchedule.class, AirlineScheduleDto.class)
//                .fieldAToB("startDateTime","startDateTime")
//                .fieldAToB("endDateTime","endDateTime").byDefault().toClassMap());
//        factory.registerClassMap(factory.classMap(AirlineScheduleDto.class, AirlineSchedule.class)
//                .fieldAToB("startDateTime","startDateTime")
//                .fieldAToB("endDateTime","endDateTime").byDefault().toClassMap());
        //if mapping like field A to B is done for the same name variables then sometimes we'll get
        //null for those
        factory.registerClassMap(factory.classMap(AirlineSchedule.class, AirlineScheduleDto.class).
                byDefault());
        factory.registerClassMap(factory.classMap(AirlineScheduleDto.class, AirlineSchedule.class)
                .byDefault());


    }
}
