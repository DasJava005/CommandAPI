package de.dasjava.commandAPI.parser.parsers.bukkit;

import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.TypeParser;
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
