package com.github.DasJava005.parser.parsers.bukkit;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

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
