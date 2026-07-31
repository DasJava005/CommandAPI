package de.dasjava.commandAPI.parser.parsers.bukkit;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class OfflinePlayerTypeParser implements TypeParser<OfflinePlayer> {

    @Override
    public OfflinePlayer parse(String value) {
        for (OfflinePlayer op : Bukkit.getOfflinePlayers()) {
            String name = op.getName();

            if (name != null && name.equalsIgnoreCase(value)) {
                return op;
            }
        }

        throw new ParseException("Unknown player: " + value);
    }

}
