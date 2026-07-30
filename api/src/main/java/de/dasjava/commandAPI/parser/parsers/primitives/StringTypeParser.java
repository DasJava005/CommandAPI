package de.dasjava.commandAPI.parser.parsers.primitives;

import de.dasjava.commandAPI.parser.TypeParser;

public class StringTypeParser implements TypeParser<String> {

    @Override
    public String parse(String value) {
        return value;
    }

}
