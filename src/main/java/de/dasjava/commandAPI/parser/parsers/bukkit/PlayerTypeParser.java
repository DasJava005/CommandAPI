package de.dasjava.commandAPI.parser.parsers.bukkit;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerTypeParser implements TypeParser<Player> {

    @Override
    public Player parse(String value) {
        return Bukkit.getPlayer(value);
    }

}
