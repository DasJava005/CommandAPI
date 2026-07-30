package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

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
