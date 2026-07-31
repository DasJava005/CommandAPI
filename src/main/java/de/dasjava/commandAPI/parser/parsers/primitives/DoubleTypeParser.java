package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

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
