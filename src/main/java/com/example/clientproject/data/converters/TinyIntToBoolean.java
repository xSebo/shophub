package com.example.clientproject.data.converters;

import javax.persistence.AttributeConverter;

public class TinyIntToBoolean implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        if (attribute) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != 0;
    }
}
