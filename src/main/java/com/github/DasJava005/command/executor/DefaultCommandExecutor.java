package com.github.DasJava005.command.executor;

import com.github.DasJava005.command.ApiCommand;
import com.github.DasJava005.command.SenderType;
import com.github.DasJava005.command.executor.feedback.CommandFeedbackProvider;
import com.github.DasJava005.command.input.TextInput;
import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.Parser;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.function.Predicate;

public final class DefaultCommandExecutor implements ApiCommandExecutor {

    private final Parser parser;
    private final CommandFeedbackProvider feedbackProvider;

    public DefaultCommandExecutor(Parser parser, CommandFeedbackProvider feedbackProvider) {
        this.parser = parser;
        this.feedbackProvider = feedbackProvider;
    }

    public boolean execute(CommandSender commandSender, String commandName, List<ApiCommand> commands, String[] args) {
        final int inputArgsLength = args.length;
        final SenderType senderType = SenderType.getFromObject(commandSender);

        final List<ApiCommand> literalMatches = getMatchingCommands(senderType, commands, args);

        if(literalMatches.isEmpty()) {
            feedbackProvider.unknownCommand(commandSender, commandName, args);
            return false;
        }
        
        ParseException exception = null;
        
        ApiCommand result = null;
        Object[] objects = null; // cache objects that are required to run the command via reflections
        commands: for(ApiCommand apiCommand : literalMatches) {
            objects = new Object[apiCommand.arguments().parameterCount() + 1];
            objects[0] = commandSender;

            int j = 1;
            for(int i = 0; i < apiCommand.arguments().getArgLength(); i++) {
                if(apiCommand.arguments().isLiteral(i)) continue;
                final Class<?> clazz = apiCommand.arguments().getArgumentType(i); //expected type in command input

                try {
                    if(clazz == TextInput.class){
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int k = i; k < inputArgsLength; k++) {
                            stringBuilder.append(" ").append(args[k]);
                        }
                        objects[j] = new TextInput(stringBuilder.toString());
                    }else{
                        objects[j] = parser.parse(clazz, args[i]); // args[i] the real "type" the user provided
                    }
                    j++;
                }catch(Exception e){
                    if(e instanceof ParseException) exception = (ParseException) e;
                    continue commands;
                }
            }

            result = apiCommand;
            break;
        }

        if(result != null) {
            String permission = result.getPermission();
            if(!commandSender.hasPermission(permission)) {
                feedbackProvider.noPermission(commandSender, commandName, args, permission);
                return false;
            }

            try {
                result.execute(objects); // use reflection to run the command
                feedbackProvider.executed(commandSender, result);
                return true;
            }catch (Exception e){
                feedbackProvider.internalError(commandSender);
                return false;
            }
        }else {
            feedbackProvider.invalidArguments(commandSender, commandName, args, exception, literalMatches.getFirst());
            return false;
        }

    }

    /**
     *
     * @return All commands that match with all the provided literals.
     */
    private List<ApiCommand> getMatchingCommands(SenderType senderType, List<ApiCommand> commands, String[] args){
        List<ApiCommand> literalMatches = commands.stream()
                .filter(senderTypeFilter(senderType))
                .filter(literalFilter(args))
                .sorted((c1, c2) -> c2.arguments().getArgLength() - c1.arguments().getArgLength())
                .toList();

        if(literalMatches.isEmpty()) {
            return Collections.emptyList();
        }
        int maxLength = literalMatches.getFirst().arguments().getArgLength();

        literalMatches = literalMatches.stream()
                .filter(c -> c.arguments().getArgLength() == maxLength)
                .toList();

        return literalMatches;
    }

    private Predicate<ApiCommand> literalFilter(String[] commandArgs){
        return c -> {
            for(int i = 0; i < c.arguments().getArgLength(); i++) {
                if(c.arguments().isLiteral(i)) {
                    if(i >= commandArgs.length) return false;
                    if(!commandArgs[i].equalsIgnoreCase(c.arguments().getArgument(i))) return false;
                }
            }
            return true;
        };
    }

    private Predicate<ApiCommand> senderTypeFilter(@NonNull SenderType senderType) {
        return c -> senderType == c.getSenderType() || c.getSenderType() == SenderType.ANY;
    }

}
