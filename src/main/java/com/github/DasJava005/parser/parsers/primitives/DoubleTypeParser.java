package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class DoubleTypeParser implements TypeParser<Double> {

    @Override
    public Double parse(String value) {
        try{
            return Double.parseDouble(value);
        }catch (NumberFormatException e){
            throw new ParseException("'" + value + "' is not a double value.");
        }
    }

}
