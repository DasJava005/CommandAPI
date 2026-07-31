package commands;

import de.dasjava.commandAPI.command.annotations.Argument;
import de.dasjava.commandAPI.command.annotations.Command;
import de.dasjava.commandAPI.command.annotations.CommandGroup;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandGroup(
        value = "balance",
        description = "A command that controls imaginary coins!",
        aliases = {"bal", "b"}
)
public class BalanceCommandTest {

    /**
     * Base command which will be uses when the user inputs: /balance
     * @param player
     */
    @Command()
    public void baseCommand(Player player) {
        player.sendMessage("Your balance is: " + ChatColor.RED + "-2026€");
    }

    @Command("give <target> <amount>")
    public void balanceCommand(Player player,
                        @Argument("target") OfflinePlayer target,
                        @Argument("amount") int amount) {

        player.sendMessage("Sent " + ChatColor.GOLD + target.getName() + ChatColor.RESET + " " + amount + " coins.");
    }

    @Command("info <target>")
    public void balanceInfoCommand(Player player, @Argument("target") OfflinePlayer target){
        int balance = 5;
        player.sendMessage(ChatColor.GOLD + target.getName() + ChatColor.GREEN + " has a total of: " + balance + " coins.");
    }

    @Command("a")
    public void aCommand(Player player) {}

    @Command("b")
    public void bCommand(Player player) {}

    @Command("c")
    public void cCommand(Player player) {}

}
