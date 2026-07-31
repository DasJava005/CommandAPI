package de.dasjava.commandAPI.command.tab;

import de.dasjava.commandAPI.command.ApiCommand;
import de.dasjava.commandAPI.command.SenderType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DefaultTabCompleter implements ApiTabCompleter {

    public List<String> complete(CommandSender commandSender, String commandName, List<ApiCommand> commands, String[] args) {
        if(!(commandSender instanceof Player)) return List.of();

        final int index = args.length;

        commands = commands.stream().filter(command -> command.getSenderType() == SenderType.PLAYER)
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
