package de.dasjava.commandAPI.command.executor.feedback;

import de.dasjava.commandAPI.command.ApiCommand;
import de.dasjava.commandAPI.parser.ParseException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public interface CommandFeedbackProvider {

    void noPermission(CommandSender commandSender, String commandName, String[] args, String requiredPermission);

    void unknownCommand(CommandSender commandSender, String commandName, String[] args);

    void invalidArguments(CommandSender commandSender, String commandName, String[] args, ParseException exception, ApiCommand apiCommand);

    default void internalError(CommandSender commandSender){
        commandSender.sendMessage(ChatColor.RED + "An internal error occurred.");
    }

    default void executed(CommandSender commandSender, ApiCommand apiCommand) {
        // optional
    }

}
