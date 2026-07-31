package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class IntegerTypeParser implements TypeParser<Integer> {

    @Override
    public Integer parse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ParseException("'" + value + "' is not an integer.");
        }
    }

}
