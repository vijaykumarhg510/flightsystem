package com.flightbooking.converter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TimeStampToLocalTime{

    public List<LocalDateTime> fromTimeStampListToLocalTimeList(List<Timestamp> timestamp){
        return timestamp.stream().map(time->time.toLocalDateTime()).
                collect(Collectors.toList());
    }
}
