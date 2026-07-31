package com.github.DasJava005.command.executor;

import com.github.DasJava005.command.ApiCommand;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.List;

@FunctionalInterface
public interface ApiCommandExecutor {

    boolean execute(CommandSender commandSender, String commandName, List<ApiCommand> commands, String[] args);

}
