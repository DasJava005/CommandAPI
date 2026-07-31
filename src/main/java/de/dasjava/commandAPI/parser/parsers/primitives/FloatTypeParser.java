package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

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
