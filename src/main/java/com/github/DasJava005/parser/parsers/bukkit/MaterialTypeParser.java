package com.github.DasJava005.parser.parsers.bukkit;

import com.github.DasJava005.parser.ParseException;
import com.github.DasJava005.parser.TypeParser;
import org.bukkit.Material;

public class MaterialTypeParser implements TypeParser<Material> {

    @Override
    public Material parse(String value) {
        Material material = Material.matchMaterial(value);

        if (material == null) {
            throw new ParseException("Invalid material: " + value);
        }

        return material;
    }

}
