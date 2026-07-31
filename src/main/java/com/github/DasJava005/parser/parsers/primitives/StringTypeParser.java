package com.github.DasJava005.parser.parsers.primitives;

import com.github.DasJava005.parser.TypeParser;

public class StringTypeParser implements TypeParser<String> {

    @Override
    public String parse(String value) {
        return value;
    }

}
