package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

public class ByteTypeParser implements TypeParser<Byte> {

    @Override
    public Byte parse(String value) {
        try{
            return Byte.parseByte(value);
        }catch (NumberFormatException e){
            throw new ParseException("'" + value + "' is not a byte value.");
        }
    }

}
