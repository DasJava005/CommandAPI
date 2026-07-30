package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

public class CharTypeParser implements TypeParser<Character> {

    @Override
    public Character parse(String value) {
        if(value.length() == 1){
            return value.charAt(0);
        }
        throw new ParseException("'" + value + "' is not a double value.");
    }

}
