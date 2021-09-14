package com.flightbooking.converter;



import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class ListToString implements AttributeConverter<List<String>,String> {
    @Override
    public String convertToDatabaseColumn(List<String> days) {

        return String.join(",",days);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {

        return new ArrayList<>(Arrays.asList(s.split(",")));
    }
}
