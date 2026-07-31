package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class ShortTypeParser implements TypeParser<Short> {

    @Override
    public Short parse(String value) {
        try{
            return Short.parseShort(value);
        }catch (NumberFormatException e){
            throw new ParseException("'" + value + "' is not a short value.");
        }
    }

}
