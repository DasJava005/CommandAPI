package com.github.DasJava005.command.executor.feedback;

import com.github.DasJava005.command.ApiCommand;
import com.github.DasJava005.parser.ParseException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Default implementation of command feedback.
 * Extend this class to customize single messages.
 */
public class DefaultFeedbackProvider implements CommandFeedbackProvider {

    @Override
    public void noPermission(CommandSender commandSender, String commandName, String[] args, String requiredPermission) {
        commandSender.sendMessage(ChatColor.RED + "You need the following permission: " + ChatColor.WHITE + requiredPermission);
    }

    @Override
    public void unknownCommand(CommandSender sender, String commandName, String[] args) {
        String input = "/" + commandName;

        if(args.length > 0) {
            input += " " + String.join(" ", args);
        }

        sender.sendMessage(ChatColor.RED + "No command found matching your input: " + ChatColor.YELLOW + input);
    }

    @Override
    public void invalidArguments(CommandSender commandSender, String commandName, String[] args, ParseException exception, ApiCommand apiCommand) {
        String exceptionMessage = "";
        if(exception != null) {
            if(exception.getMessage() != null) {
                exceptionMessage = " Reason: " + exception.getMessage();
            }
        }

        commandSender.sendMessage(ChatColor.RED + "Invalid arguments." + exceptionMessage);
        commandSender.sendMessage(ChatColor.RED + "Usage of this command is: " + ChatColor.GOLD + "/" + apiCommand.getCommandSyntax());
    }

}
