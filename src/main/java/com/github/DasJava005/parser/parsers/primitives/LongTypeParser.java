package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class LongTypeParser implements TypeParser<Long> {

    @Override
    public Long parse(String value) {
        try{
            return Long.parseLong(value);
        }catch (NumberFormatException e){
            throw new ParseException("'" + value + "' is not a long value.");
        }
    }

}
