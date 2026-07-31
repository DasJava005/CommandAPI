package de.dasjava.commandAPI.command.executor;

import de.dasjava.commandAPI.command.ApiCommand;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.List;

@FunctionalInterface
public interface ApiCommandExecutor {

    boolean execute(CommandSender commandSender, String commandName, List<ApiCommand> commands, String[] args);

}
