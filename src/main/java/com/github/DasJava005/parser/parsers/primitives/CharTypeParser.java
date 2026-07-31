package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class CharTypeParser implements TypeParser<Character> {

    @Override
    public Character parse(String value) {
        if(value.length() == 1){
            return value.charAt(0);
        }
        throw new ParseException("'" + value + "' is not a double value.");
    }

}
