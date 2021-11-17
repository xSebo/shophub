package com.example.clientproject.data.converters;

import javax.persistence.AttributeConverter;

/**
 * Converter for TinyInt to Boolean values
 * Implements the "AttributeConverter" Interface with types "Boolean" and "Integer"
 */
public class TinyIntToBoolean implements AttributeConverter<Boolean, Integer> {
    /**
     * Overrides a method to convert from boolean to int
     * @param attribute - the value to convert
     * @return the converted value
     */
    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        if (attribute) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Overrides a method to convert from int to boolean
     * @param dbData - the value to convert
     * @return the converted value
     */
    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != 0;
    }
}
