package commands;

import de.dasjava.commandAPI.command.annotations.Argument;
import de.dasjava.commandAPI.command.annotations.Command;
import de.dasjava.commandAPI.command.annotations.CommandGroup;
import de.dasjava.commandAPI.command.input.TextInput;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandGroup(value = "message", aliases = {"msg"})
public class MessageCommandTest {

    @Command()
    public void baseCommand(Player player) {
        player.sendMessage("Usage: /message <Player> <Your message>");
    }

    @Command("<target> <message>")
    public void messageCommand(Player player, @Argument("target") Player receiver, @Argument("message") TextInput text) {
        if(receiver == null) {
            player.sendMessage("Player not found");
            return;
        }
        receiver.sendMessage(ChatColor.GREEN + "Message from " + ChatColor.YELLOW + receiver.getName() + ChatColor.GRAY + text.text());
    }

}
