package com.github.DasJava005.parser.parsers.bukkit;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;
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
