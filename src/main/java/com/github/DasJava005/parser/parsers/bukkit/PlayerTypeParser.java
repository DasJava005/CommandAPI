package com.github.DasJava005.parser.parsers.bukkit;

import com.github.DasJava005.parser.TypeParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerTypeParser implements TypeParser<Player> {

    @Override
    public Player parse(String value) {
        return Bukkit.getPlayer(value);
    }

}
