package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

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
