package com.github.DasJava005.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Optional;

public enum SenderType {
    ANY,
    PLAYER,
    CONSOLE;

    public static Optional<SenderType> getFromClass(Class<?> clazz) {
        if (clazz == null || !CommandSender.class.isAssignableFrom(clazz)) {
            return Optional.empty();
        }

        if (ConsoleCommandSender.class.isAssignableFrom(clazz)) {
            return Optional.of(CONSOLE);
        }

        if (Player.class.isAssignableFrom(clazz)) {
            return Optional.of(PLAYER);
        }

        return Optional.of(ANY);
    }

    public static <T extends CommandSender> SenderType getFromObject(T object) {
        Objects.requireNonNull(object, "CommandSender object must not be null");
        return getFromClass(object.getClass()).orElseThrow();
    }

}
