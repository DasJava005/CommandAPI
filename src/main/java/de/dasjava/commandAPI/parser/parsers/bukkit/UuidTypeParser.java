package de.dasjava.commandAPI.parser.parsers.bukkit;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;

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
