package de.dasjava.commandAPI.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class ApiTabCompleter {

    private final CommandRegistry registry;

    public ApiTabCompleter(CommandRegistry registry) {
        this.registry = registry;
    }

    public List<String> onTabComplete(@NonNull CommandSender commandSender, @NonNull String commandName, String @NonNull [] args) {
        if(!(commandSender instanceof Player)) return List.of();

        final int index = args.length;

        List<ApiCommand> commands = registry.getCommands(commandName).stream()
                .filter(command -> command.getSenderType() == SenderType.PLAYER)
                .filter(command -> command.arguments().getArgLength() >= index)
                .filter(command -> {
                    for(int i = 0; i < index; i++) {
                        if(!command.arguments().isLiteral(i))continue;
                        if(!command.arguments().getArgument(i).startsWith(args[i])){
                            return false;
                        }
                    }

                    return true;
                })
                .toList();

        List<String> completions = new ArrayList<>();
        commands.forEach(command -> {
            if(command.arguments().getArgLength() >= index){
                completions.add(command.arguments().getArgument(index - 1));
            }
        });

        return completions;
    }

}
