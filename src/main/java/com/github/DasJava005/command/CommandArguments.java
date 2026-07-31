package com.github.DasJava005.command;

import java.util.Arrays;
import java.util.Objects;

public class CommandArguments {

    private final String[] arguments;
    private final Class<?>[] argumentTypes;

    private final int parameters; // the amount of user input fields

    //argumentType == null will be defined as a command literal
    public CommandArguments(String[] arguments, Class<?>[] argumentTypes) {
        this.arguments = arguments;
        this.argumentTypes = argumentTypes;

        if(arguments.length != argumentTypes.length) {
            throw new IllegalArgumentException("Number of arguments and argumentTypes don't match");
        }

        this.parameters = Arrays.stream(this.argumentTypes)
                .filter(Objects::nonNull)
                .toList()
                .size();
    }

    public int getArgLength() {
        return this.arguments.length;
    }

    public String getArgument(int index) {
        if(index < 0 || index >= arguments.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return arguments[index];
    }

    public String[] getArguments(){
        return this.arguments;
    }

    public Class<?> getArgumentType(int index) {
        if(index < 0 || index >= argumentTypes.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return argumentTypes[index];
    }

    public boolean isLiteral(int index) {
        if(index >= arguments.length || index < 0) return false;
        return this.argumentTypes[index] == null;
    }

    public int parameterCount() {
        return parameters;
    }

}
