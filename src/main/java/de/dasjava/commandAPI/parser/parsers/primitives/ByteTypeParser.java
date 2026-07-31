package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

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
