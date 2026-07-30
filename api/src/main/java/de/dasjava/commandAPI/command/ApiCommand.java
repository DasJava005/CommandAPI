package de.dasjava.commandAPI.command;

import de.dasjava.commandAPI.command.annotations.CommandGroup;

import java.lang.reflect.Method;

public final class ApiCommand {

    private final Object instance;
    private final Method method;

    private final String commandNamespace;
    private final String description;
    private final String[] aliases;

    private final SenderType senderType;

    private final CommandArguments arguments;

    public ApiCommand(Object instance,
                      Method method,
                      SenderType senderType,
                      String[] arguments,
                      Class<?>[] argTypes,
                      String description,
                      String[] aliases) {

        this.instance = instance;
        this.method = method;
        this.senderType = senderType;

        this.commandNamespace = instance.getClass().getAnnotation(CommandGroup.class).value();
        this.description = description;
        this.aliases = aliases;

        this.arguments = new CommandArguments(arguments, argTypes);
    }

    public void execute(Object[] arguments) throws ReflectiveOperationException {
        this.method.invoke(this.instance, arguments);
    }

    public String getCommandName() {
        return this.commandNamespace;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public SenderType getSenderType() {
        return senderType;
    }

    public CommandArguments arguments() {
        return arguments;
    }

    public String getCommandUsage(){
        StringBuilder syntax = new StringBuilder("/" + this.commandNamespace);
        for(String arg : arguments.getArguments()){
            syntax.append(" ").append(arg);
        }
        return syntax.toString();
    }

}