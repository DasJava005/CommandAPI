package commands;

import de.dasjava.commandAPI.command.annotations.Argument;
import de.dasjava.commandAPI.command.annotations.Command;
import de.dasjava.commandAPI.command.annotations.CommandGroup;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandGroup("item")
public class ItemCommandTest {

    @Command("")
    public void baseCommand(Player player) {
        player.sendMessage("Usage: /item get <material> <amount>");
    }

    @Command("get <material> <amount>")
    public void itemCommand(Player player, @Argument("material") Material mat, @Argument("amount") int amount) {
        ItemStack item = new ItemStack(mat, amount);
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN + "Received x" + amount + " " + mat);
    }

    @Command(value = "enchant <enchantment> <level>", permission = "command.item.enchant")
    public void enchantCommand(Player player, @Argument("enchantment") Enchantment enchantment, @Argument("level") int level) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item.getType() == Material.AIR){
            player.sendMessage(ChatColor.RED + "You need to hold an item!");
            return;
        }
        item.addUnsafeEnchantment(enchantment, level);
        player.sendMessage(ChatColor.GREEN + "Added enchantment: " + enchantment.toString() + " with level: " + level + "to your item.");
    }

}
