package com.github.DasJava005.parser.parsers.bukkit;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

public class EnchantmentTypeParser implements TypeParser<Enchantment> {

    @Override
    public Enchantment parse(String value) {
        NamespacedKey key = NamespacedKey.minecraft(value.toLowerCase());

        Registry<Enchantment> enchantmentRegistry = Bukkit.getRegistry(Enchantment.class);
        if(enchantmentRegistry == null){
            throw new ParseException("Invalid enchantment: " + value);
        }

        final Enchantment enchantment = enchantmentRegistry.get(key);

        if (enchantment == null) {
            throw new ParseException("Invalid enchantment: " + value);
        }

        return enchantment;
    }

}
