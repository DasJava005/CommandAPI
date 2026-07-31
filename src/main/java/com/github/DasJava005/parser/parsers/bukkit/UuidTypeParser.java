package com.github.DasJava005.parser.parsers.bukkit;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;

import java.util.UUID;

public class UuidTypeParser implements TypeParser<UUID> {

    @Override
    public UUID parse(String string) {
        try {
            return UUID.fromString(string);
        } catch (IllegalArgumentException e) {
            throw new ParseException("'" + string + "' is not a valid UUID.");
        }
    }

}
