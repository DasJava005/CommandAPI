package com.github.DasJava005.command.tab;

import com.github.DasJava005.command.ApiCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

@FunctionalInterface
public interface ApiTabCompleter {

    /**
     * Creates tab completion suggestions for a command input.
     *
     * @param commandSender the sender requesting tab completion
     * @param commandName the command name or namespace
     * @param commands all registered commands matching the given namespace
     * @param args the arguments already typed by the user, excluding the command name
     * @return a list of possible completions
     */
    List<String> complete(CommandSender commandSender,
                          String commandName,
                          List<ApiCommand> commands,
                          String[] args);

}
