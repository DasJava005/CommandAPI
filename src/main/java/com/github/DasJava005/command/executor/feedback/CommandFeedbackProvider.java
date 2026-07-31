package com.github.DasJava005.command.executor.feedback;

import com.github.DasJava005.command.ApiCommand;
import com.github.DasJava005.parser.ParseException;
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
