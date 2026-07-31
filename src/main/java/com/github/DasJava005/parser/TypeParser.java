package com.github.DasJava005.parser;

@FunctionalInterface
public interface TypeParser<T> {
    public abstract T parse(String value);
}
