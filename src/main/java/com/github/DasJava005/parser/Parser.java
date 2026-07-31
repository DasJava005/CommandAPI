package com.github.DasJava005.parser;

import com.github.DasJava005.parser.parsers.bukkit.*;
import com.github.DasJava005.parser.parsers.primitives.*;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Parser {

    private final Map<Class<?>, TypeParser<?>> parsers = new HashMap<>();

    private <T> void registerParser(Class<T> type, TypeParser<T> parser) {
        parsers.put(type, parser);
    }

    public final Object parse(Class<?> type, String value) throws IllegalArgumentException, ParseException {
        type = wrap(type);
        TypeParser<?> parser = parsers.get(type);

        if (parser == null) {
            throw new IllegalArgumentException("No parser for type: " + type.getName());
        }

        return parser.parse(value);
    }

    private Class<?> wrap(Class<?> type) {
        if (!type.isPrimitive()) {
            return type;
        }

        if (type == int.class) return Integer.class;
        if (type == boolean.class) return Boolean.class;
        if (type == double.class) return Double.class;
        if (type == long.class) return Long.class;
        if (type == float.class) return Float.class;
        if (type == short.class) return Short.class;
        if (type == byte.class) return Byte.class;
        if (type == char.class) return Character.class;

        return type;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private final Parser parser = new Parser();

        private Builder(){}

        public Builder defaults() {
            parser.registerParser(String.class, new StringTypeParser());
            parser.registerParser(Integer.class, new IntegerTypeParser());
            parser.registerParser(Boolean.class, new BooleanTypeParser());
            parser.registerParser(Double.class, new DoubleTypeParser());
            parser.registerParser(Float.class, new FloatTypeParser());
            parser.registerParser(Short.class, new ShortTypeParser());
            parser.registerParser(Byte.class, new ByteTypeParser());
            parser.registerParser(Long.class, new LongTypeParser());
            parser.registerParser(Character.class, new CharTypeParser());
            return this;
        }

        public <T> Builder registerTypeParser(Class<T> clazz, TypeParser<T> typeParser) {
            parser.registerParser(clazz, typeParser);
            return this;
        }

        public Parser build() {
            return parser;
        }
    }

    public static Parser createDefaultParser() {
        return Parser.builder()
                .defaults()
                .build();
    }

    public static Parser createDefaultBukkitParser() {
        return Parser.builder()
                .defaults()
                .registerTypeParser(Player.class, new PlayerTypeParser())
                .registerTypeParser(UUID.class, new UuidTypeParser())
                .registerTypeParser(OfflinePlayer.class, new OfflinePlayerTypeParser())
                .registerTypeParser(GameMode.class, new GameModeTypeParser())
                .registerTypeParser(Material.class, new MaterialTypeParser())
                .registerTypeParser(Enchantment.class, new EnchantmentTypeParser())
                .build();
    }

}
