package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class FloatTypeParser implements TypeParser<Float> {

    @Override
    public Float parse(String value) {
        try{
            return Float.parseFloat(value);
        }catch (NumberFormatException e){
            throw new ParseException("'" + value + "' is not a float value.");
        }
    }

}
