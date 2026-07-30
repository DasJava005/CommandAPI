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

    public String getCommandSyntax(){
        StringBuilder syntax = new StringBuilder(this.commandNamespace);
        for(String arg : arguments.getArguments()){
            syntax.append(" ").append(arg);
        }
        return syntax.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(!(obj instanceof ApiCommand other)){
            return false;
        }

        if(!this.commandNamespace.equals(other.commandNamespace)) return false;
        if(this.senderType != other.senderType) return false;

        if(this.arguments.getArgLength() != other.arguments.getArgLength()) return false;

        for(int i = 0; i < this.arguments.getArgLength(); i++){
            if(arguments().isLiteral(i) != other.arguments().isLiteral(i)) return false;
            if(arguments().isLiteral(i)){
                if(!arguments().getArgument(i).equals(other.arguments().getArgument(i))) return false;

            }else{
                if(arguments().getArgumentType(i) != other.arguments().getArgumentType(i)) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = commandNamespace.hashCode();

        result = 31 * result + senderType.hashCode();
        result = 31 * result + arguments.getArgLength();

        for(int i = 0; i < arguments.getArgLength(); i++) {
            if(arguments.isLiteral(i)) {
                result = 31 * result + arguments.getArgument(i).hashCode();
            } else {
                result = 31 * result + arguments.getArgumentType(i).hashCode();
            }
        }

        return result;
    }

}