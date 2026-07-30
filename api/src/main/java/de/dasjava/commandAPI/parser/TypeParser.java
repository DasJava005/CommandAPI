package de.dasjava.commandAPI.parser;

@FunctionalInterface
public interface TypeParser<T> {
    public abstract T parse(String value);
}
