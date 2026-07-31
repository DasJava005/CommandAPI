package de.dasjava.commandAPI.parser.parsers.bukkit;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;
import org.bukkit.GameMode;

public class GameModeTypeParser implements TypeParser<GameMode> {

    @Override
    public GameMode parse(String value) {
        return switch (value.toLowerCase()) {
            case "survival", "s", "0" -> GameMode.SURVIVAL;
            case "creative", "c", "1" -> GameMode.CREATIVE;
            case "adventure", "a", "2" -> GameMode.ADVENTURE;
            case "spectator", "sp", "3" -> GameMode.SPECTATOR;
            default -> throw new ParseException("Invalid game mode: " + value);
        };
    }

}
